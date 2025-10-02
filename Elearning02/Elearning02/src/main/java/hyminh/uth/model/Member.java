package hyminh.uth.model;

import java.time.LocalDate;
import java.util.*;

public class Member extends User {
    private String memberId;
    private LocalDate registrationDate;
    private Subscription currentSubscription;
    private List<String> workoutScheduleIds;
    private List<String> attendanceIds;
    private Progress progress;

    public Member(String userId, String username, String password, String email,
                  String phone, String memberId) {
        super(userId, username, password, email, phone);
        this.role = "MEMBER";
        this.memberId = memberId;
        this.registrationDate = LocalDate.now();
        this.workoutScheduleIds = new ArrayList<>();
        this.attendanceIds = new ArrayList<>();
        this.progress = new Progress("PROG-" + memberId, memberId, LocalDate.now());
    }

    public String getMemberId() { return memberId; }
    public LocalDate getRegistrationDate() { return registrationDate; }
    public Subscription getCurrentSubscription() { return currentSubscription; }
    public void setCurrentSubscription(Subscription sub) { this.currentSubscription = sub; }
    public Progress getProgress() { return progress; }
    public List<String> getWorkoutScheduleIds() { return workoutScheduleIds; }
    public List<String> getAttendanceIds() { return attendanceIds; }

    public void addWorkoutSchedule(String scheduleId) {
        workoutScheduleIds.add(scheduleId);
    }

    public void addAttendance(String attendanceId) {
        attendanceIds.add(attendanceId);
    }

    public boolean isSubscriptionActive() {
        return currentSubscription != null && !currentSubscription.isExpired();
    }

    @Override
    public String toString() {
        return "Member{" + "memberId='" + memberId + "', username='" + username +
                "', email='" + email + "', subscription=" +
                (currentSubscription != null ? currentSubscription.getStatus() : "None") + "}";
    }
}