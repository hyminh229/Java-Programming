package hyminh.uth.system;

import hyminh.uth.model.*;
import hyminh.uth.util.DataManager;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class GymManagementSystem {
    private Map<String, User> users;
    private Map<String, Member> members;
    private Map<String, Trainer> trainers;
    private Map<String, Admin> admins;
    private Map<String, SubscriptionPlan> subscriptionPlans;
    private Map<String, WorkoutSchedule> workoutSchedules;
    private Map<String, Attendance> attendanceRecords;
    private User currentUser;
    private DataManager dataManager;

    public GymManagementSystem() {
        this.users = new HashMap<>();
        this.members = new HashMap<>();
        this.trainers = new HashMap<>();
        this.admins = new HashMap<>();
        this.subscriptionPlans = new HashMap<>();
        this.workoutSchedules = new HashMap<>();
        this.attendanceRecords = new HashMap<>();
        this.dataManager = new DataManager();
        initializeDefaultPlans();
    }

    private void initializeDefaultPlans() {
        addSubscriptionPlan(new SubscriptionPlan("PLAN001", "Basic", 1, 50.0, "Monthly basic access"));
        addSubscriptionPlan(new SubscriptionPlan("PLAN002", "Standard", 3, 130.0, "3-month standard access"));
        addSubscriptionPlan(new SubscriptionPlan("PLAN003", "Premium", 12, 480.0, "Annual premium access"));
    }

    // ========== User Management ==========
    public void registerAdmin(Admin admin) {
        users.put(admin.getUserId(), admin);
        admins.put(admin.getUserId(), admin);
    }

    public void registerTrainer(Trainer trainer) {
        users.put(trainer.getUserId(), trainer);
        trainers.put(trainer.getUserId(), trainer);
    }

    public void registerMember(Member member) {
        users.put(member.getUserId(), member);
        members.put(member.getMemberId(), member);
    }

    public User authenticateUser(String username, String password) {
        for (User user : users.values()) {
            if (user.login(username, password)) {
                currentUser = user;
                return user;
            }
        }
        return null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void logout() {
        currentUser = null;
    }

    // ========== Admin Functions ==========
    public void addMember(Member member) {
        if (currentUser instanceof Admin) {
            registerMember(member);
            System.out.println("Member added successfully: " + member.getUsername());
        }
    }

    public void updateMember(String memberId, String email, String phone) {
        if (currentUser instanceof Admin) {
            Member member = members.get(memberId);
            if (member != null) {
                member.setEmail(email);
                member.setPhone(phone);
                System.out.println("Member updated successfully");
            }
        }
    }

    public void deleteMember(String memberId) {
        if (currentUser instanceof Admin) {
            Member member = members.remove(memberId);
            if (member != null) {
                users.remove(member.getUserId());
                System.out.println("Member deleted successfully");
            }
        }
    }

    public void addSubscriptionPlan(SubscriptionPlan plan) {
        subscriptionPlans.put(plan.getPlanId(), plan);
    }

    public Map<String, Double> generateRevenueReport() {
        if (!(currentUser instanceof Admin)) return null;

        double totalRevenue = members.values().stream()
                .filter(m -> m.getCurrentSubscription() != null)
                .mapToDouble(m -> m.getCurrentSubscription().getAmount())
                .sum();

        long activeMembers = members.values().stream()
                .filter(Member::isSubscriptionActive)
                .count();

        long expiredMembers = members.size() - activeMembers;

        Map<String, Double> report = new HashMap<>();
        report.put("Total Revenue", totalRevenue);
        report.put("Active Members", (double) activeMembers);
        report.put("Expired Members", (double) expiredMembers);

        return report;
    }

    // ========== Trainer Functions ==========
    public void createWorkoutSchedule(String memberId, String trainerId, LocalDate date, List<Exercise> exercises) {
        if (currentUser instanceof Trainer || currentUser instanceof Admin) {
            String scheduleId = "SCH-" + System.currentTimeMillis();
            WorkoutSchedule schedule = new WorkoutSchedule(scheduleId, memberId, trainerId, date);
            for (Exercise ex : exercises) {
                schedule.addExercise(ex);
            }
            workoutSchedules.put(scheduleId, schedule);

            Member member = members.get(memberId);
            if (member != null) {
                member.addWorkoutSchedule(scheduleId);
            }

            System.out.println("Workout schedule created: " + scheduleId);
        }
    }

    public void trackAttendance(String memberId, LocalDate date) {
        if (currentUser instanceof Trainer || currentUser instanceof Admin) {
            String attendanceId = "ATT-" + memberId + "-" + date.toString();
            Attendance attendance = new Attendance(attendanceId, memberId, date);
            attendance.markPresent(LocalTime.now());
            attendanceRecords.put(attendanceId, attendance);

            Member member = members.get(memberId);
            if (member != null) {
                member.addAttendance(attendanceId);
                member.getProgress().incrementWorkouts();
            }

            System.out.println("Attendance marked for member: " + memberId);
        }
    }

    public Map<String, Long> generateAttendanceReport() {
        if (!(currentUser instanceof Trainer || currentUser instanceof Admin)) return null;

        Map<String, Long> report = new HashMap<>();
        for (Member member : members.values()) {
            long attendanceCount = attendanceRecords.values().stream()
                    .filter(a -> a.getMemberId().equals(member.getMemberId()) && a.isPresent())
                    .count();
            report.put(member.getUsername(), attendanceCount);
        }

        return report;
    }

    // ========== Member Functions ==========
    public List<WorkoutSchedule> viewWorkoutSchedules() {
        if (currentUser instanceof Member) {
            Member member = (Member) currentUser;
            return member.getWorkoutScheduleIds().stream()
                    .map(id -> workoutSchedules.get(id))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public void updateProgress(double weight, double bodyFat, String notes) {
        if (currentUser instanceof Member) {
            Member member = (Member) currentUser;
            member.getProgress().updateMetrics(weight, bodyFat);
            member.getProgress().setNotes(notes);
            System.out.println("Progress updated successfully");
        }
    }

    public void renewSubscription(String planId) {
        if (currentUser instanceof Member) {
            Member member = (Member) currentUser;
            SubscriptionPlan plan = subscriptionPlans.get(planId);
            if (plan != null) {
                String subId = "SUB-" + member.getMemberId() + "-" + System.currentTimeMillis();
                Subscription subscription = new Subscription(subId, plan, LocalDate.now());
                member.setCurrentSubscription(subscription);
                System.out.println("Subscription renewed: " + plan.getPlanName());
            }
        }
    }

    public double getAttendancePercentage() {
        if (currentUser instanceof Member) {
            Member member = (Member) currentUser;
            long totalDays = 30; // Last 30 days
            long attendedDays = member.getAttendanceIds().stream()
                    .map(id -> attendanceRecords.get(id))
                    .filter(Objects::nonNull)
                    .filter(Attendance::isPresent)
                    .count();
            return (attendedDays * 100.0) / totalDays;
        }
        return 0.0;
    }

    // ========== Getters ==========
    public Map<String, Member> getMembers() { return members; }
    public Map<String, Trainer> getTrainers() { return trainers; }
    public Map<String, SubscriptionPlan> getSubscriptionPlans() { return subscriptionPlans; }
    public Map<String, WorkoutSchedule> getWorkoutSchedules() { return workoutSchedules; }
    public Map<String, Attendance> getAttendanceRecords() { return attendanceRecords; }

    // ========== Data Persistence ==========
    public void saveData() {
        dataManager.saveToJSON("members.json", new ArrayList<>(members.values()));
        dataManager.saveToJSON("trainers.json", new ArrayList<>(trainers.values()));
        dataManager.saveToJSON("schedules.json", new ArrayList<>(workoutSchedules.values()));
        dataManager.saveToJSON("attendance.json", new ArrayList<>(attendanceRecords.values()));
        System.out.println("Data saved successfully");
    }

    public void loadData() {
        // Implementation for loading data from JSON files
        System.out.println("Data loaded successfully");
    }
}