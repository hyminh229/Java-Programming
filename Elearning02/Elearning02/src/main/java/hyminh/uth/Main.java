package hyminh.uth;

import hyminh.uth.model.*;
import hyminh.uth.system.GymManagementSystem;
import java.time.LocalDate;
import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static GymManagementSystem gms = new GymManagementSystem();

    public static void main(String[] args) {
        initializeTestData();

        System.out.println("========================================");
        System.out.println("   GYM MANAGEMENT SYSTEM");
        System.out.println("========================================\n");

        while (true) {
            if (gms.getCurrentUser() == null) {
                showLoginMenu();
            } else {
                User user = gms.getCurrentUser();
                if (user instanceof Admin) {
                    showAdminMenu();
                } else if (user instanceof Trainer) {
                    showTrainerMenu();
                } else if (user instanceof Member) {
                    showMemberMenu();
                }
            }
        }
    }

    private static void initializeTestData() {
        // Create Admin
        Admin admin = new Admin("ADM001", "admin", "admin123", "admin@gym.com", "1234567890");
        gms.registerAdmin(admin);

        // Create Trainer
        Trainer trainer = new Trainer("TRN001", "trainer1", "trainer123", "trainer@gym.com",
                "0987654321", "Strength Training");
        gms.registerTrainer(trainer);

        // Create Members
        Member member1 = new Member("USR001", "john_doe", "pass123", "john@email.com",
                "1111111111", "MEM001");
        Member member2 = new Member("USR002", "jane_smith", "pass123", "jane@email.com",
                "2222222222", "MEM002");
        gms.registerMember(member1);
        gms.registerMember(member2);

        // Assign subscriptions
        member1.setCurrentSubscription(new Subscription("SUB001",
                gms.getSubscriptionPlans().get("PLAN002"), LocalDate.now()));
        member2.setCurrentSubscription(new Subscription("SUB002",
                gms.getSubscriptionPlans().get("PLAN003"), LocalDate.now()));
    }

    private static void showLoginMenu() {
        System.out.println("\n=== LOGIN ===");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user = gms.authenticateUser(username, password);
        if (user != null) {
            System.out.println("\n✓ Login successful! Welcome, " + user.getUsername());
        } else {
            System.out.println("\n✗ Invalid credentials. Please try again.");
        }
    }

    private static void showAdminMenu() {
        System.out.println("\n=== ADMIN MENU ===");
        System.out.println("1. View All Members");
        System.out.println("2. Add New Member");
        System.out.println("3. Delete Member");
        System.out.println("4. View Subscription Plans");
        System.out.println("5. Generate Revenue Report");
        System.out.println("6. Generate Attendance Report");
        System.out.println("7. Save Data");
        System.out.println("8. Logout");
        System.out.print("Choose option: ");

        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1: viewAllMembers(); break;
            case 2: addNewMember(); break;
            case 3: deleteMember(); break;
            case 4: viewSubscriptionPlans(); break;
            case 5: generateRevenueReport(); break;
            case 6: generateAttendanceReport(); break;
            case 7: gms.saveData(); break;
            case 8: gms.logout(); System.out.println("Logged out successfully"); break;
            default: System.out.println("Invalid option");
        }
    }

    private static void showTrainerMenu() {
        System.out.println("\n=== TRAINER MENU ===");
        System.out.println("1. Create Workout Schedule");
        System.out.println("2. Track Member Attendance");
        System.out.println("3. View Attendance Report");
        System.out.println("4. View All Members");
        System.out.println("5. Logout");
        System.out.print("Choose option: ");

        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1: createWorkoutSchedule(); break;
            case 2: trackAttendance(); break;
            case 3: generateAttendanceReport(); break;
            case 4: viewAllMembers(); break;
            case 5: gms.logout(); System.out.println("Logged out successfully"); break;
            default: System.out.println("Invalid option");
        }
    }

    private static void showMemberMenu() {
        System.out.println("\n=== MEMBER MENU ===");
        System.out.println("1. View My Workout Schedules");
        System.out.println("2. Update Progress");
        System.out.println("3. Renew Subscription");
        System.out.println("4. View Subscription Status");
        System.out.println("5. View Attendance Percentage");
        System.out.println("6. Logout");
        System.out.print("Choose option: ");

        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1: viewWorkoutSchedules(); break;
            case 2: updateProgress(); break;
            case 3: renewSubscription(); break;
            case 4: viewSubscriptionStatus(); break;
            case 5: viewAttendancePercentage(); break;
            case 6: gms.logout(); System.out.println("Logged out successfully"); break;
            default: System.out.println("Invalid option");
        }
    }

    private static void viewAllMembers() {
        System.out.println("\n=== ALL MEMBERS ===");
        for (Member member : gms.getMembers().values()) {
            System.out.println(member);
        }
    }

    private static void addNewMember() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Phone: ");
        String phone = scanner.nextLine();

        String userId = "USR" + (gms.getMembers().size() + 100);
        String memberId = "MEM" + (gms.getMembers().size() + 100);

        Member member = new Member(userId, username, password, email, phone, memberId);
        gms.addMember(member);
    }

    private static void deleteMember() {
        System.out.print("Enter Member ID to delete: ");
        String memberId = scanner.nextLine();
        gms.deleteMember(memberId);
    }

    private static void viewSubscriptionPlans() {
        System.out.println("\n=== SUBSCRIPTION PLANS ===");
        for (SubscriptionPlan plan : gms.getSubscriptionPlans().values()) {
            System.out.println(plan);
        }
    }

    private static void generateRevenueReport() {
        Map<String, Double> report = gms.generateRevenueReport();
        System.out.println("\n=== REVENUE REPORT ===");
        for (Map.Entry<String, Double> entry : report.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static void generateAttendanceReport() {
        Map<String, Long> report = gms.generateAttendanceReport();
        System.out.println("\n=== ATTENDANCE REPORT ===");
        for (Map.Entry<String, Long> entry : report.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " days");
        }
    }

    private static void createWorkoutSchedule() {
        System.out.print("Enter Member ID: ");
        String memberId = scanner.nextLine();

        List<Exercise> exercises = new ArrayList<>();
        System.out.print("Number of exercises: ");
        int numExercises = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < numExercises; i++) {
            System.out.println("\nExercise " + (i + 1) + ":");
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Type (Cardio/Strength/Flexibility): ");
            String type = scanner.nextLine();
            System.out.print("Sets: ");
            int sets = Integer.parseInt(scanner.nextLine());
            System.out.print("Reps: ");
            int reps = Integer.parseInt(scanner.nextLine());
            System.out.print("Description: ");
            String desc = scanner.nextLine();

            exercises.add(new Exercise("EX" + i, name, type, sets, reps, desc));
        }

        gms.createWorkoutSchedule(memberId, gms.getCurrentUser().getUserId(),
                LocalDate.now(), exercises);
    }

    private static void trackAttendance() {
        System.out.print("Enter Member ID: ");
        String memberId = scanner.nextLine();
        gms.trackAttendance(memberId, LocalDate.now());
    }

    private static void viewWorkoutSchedules() {
        List<WorkoutSchedule> schedules = gms.viewWorkoutSchedules();
        System.out.println("\n=== MY WORKOUT SCHEDULES ===");
        if (schedules.isEmpty()) {
            System.out.println("No workout schedules assigned yet.");
        } else {
            for (WorkoutSchedule schedule : schedules) {
                System.out.println("\n" + schedule);
                System.out.println("Exercises:");
                for (Exercise ex : schedule.getExercises()) {
                    System.out.println("  - " + ex);
                }
            }
        }
    }

    private static void updateProgress() {
        System.out.print("Weight (kg): ");
        double weight = Double.parseDouble(scanner.nextLine());
        System.out.print("Body Fat (%): ");
        double bodyFat = Double.parseDouble(scanner.nextLine());
        System.out.print("Notes: ");
        String notes = scanner.nextLine();

        gms.updateProgress(weight, bodyFat, notes);
    }

    private static void renewSubscription() {
        viewSubscriptionPlans();
        System.out.print("\nEnter Plan ID to subscribe: ");
        String planId = scanner.nextLine();
        gms.renewSubscription(planId);
    }

    private static void viewSubscriptionStatus() {
        Member member = (Member) gms.getCurrentUser();
        if (member.getCurrentSubscription() != null) {
            System.out.println("\n=== SUBSCRIPTION STATUS ===");
            System.out.println(member.getCurrentSubscription());
        } else {
            System.out.println("\nNo active subscription.");
        }
    }

    private static void viewAttendancePercentage() {
        double percentage = gms.getAttendancePercentage();
        System.out.println("\nYour attendance percentage (last 30 days): " +
                String.format("%.2f", percentage) + "%");
    }
}