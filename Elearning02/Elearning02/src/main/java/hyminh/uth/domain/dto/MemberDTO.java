package hyminh.uth.domain.dto;

import hyminh.uth.domain.valueobject.MemberId;
import hyminh.uth.domain.valueobject.UserRole;
import hyminh.uth.domain.valueobject.SubscriptionStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Data Transfer Object for Member entities.
 * This class represents a member's data for transfer between layers.
 * 
 * @author Gym Management System
 * @version 1.0
 */
public class MemberDTO {
    
    private final String userId;
    private final String username;
    private final String email;
    private final String phone;
    private final UserRole role;
    private final MemberId memberId;
    private final LocalDate registrationDate;
    private final String subscriptionId;
    private final SubscriptionStatus subscriptionStatus;
    private final LocalDate subscriptionStartDate;
    private final LocalDate subscriptionEndDate;
    private final double weight;
    private final double bodyFat;
    private final int workoutsCompleted;
    private final int workoutScheduleCount;
    private final int attendanceCount;
    private final boolean isActive;
    private final LocalDateTime createdAt;
    private final LocalDateTime lastModifiedAt;
    
    /**
     * Constructs a new MemberDTO with the specified parameters.
     * 
     * @param userId the user ID
     * @param username the username
     * @param email the email
     * @param phone the phone
     * @param role the user role
     * @param memberId the member ID
     * @param registrationDate the registration date
     * @param subscriptionId the subscription ID
     * @param subscriptionStatus the subscription status
     * @param subscriptionStartDate the subscription start date
     * @param subscriptionEndDate the subscription end date
     * @param weight the current weight
     * @param bodyFat the current body fat percentage
     * @param workoutsCompleted the number of workouts completed
     * @param workoutScheduleCount the number of workout schedules
     * @param attendanceCount the number of attendance records
     * @param isActive whether the member is active
     * @param createdAt the creation timestamp
     * @param lastModifiedAt the last modification timestamp
     */
    public MemberDTO(String userId, String username, String email, String phone,
                     UserRole role, MemberId memberId, LocalDate registrationDate,
                     String subscriptionId, SubscriptionStatus subscriptionStatus,
                     LocalDate subscriptionStartDate, LocalDate subscriptionEndDate,
                     double weight, double bodyFat, int workoutsCompleted,
                     int workoutScheduleCount, int attendanceCount, boolean isActive,
                     LocalDateTime createdAt, LocalDateTime lastModifiedAt) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.memberId = memberId;
        this.registrationDate = registrationDate;
        this.subscriptionId = subscriptionId;
        this.subscriptionStatus = subscriptionStatus;
        this.subscriptionStartDate = subscriptionStartDate;
        this.subscriptionEndDate = subscriptionEndDate;
        this.weight = weight;
        this.bodyFat = bodyFat;
        this.workoutsCompleted = workoutsCompleted;
        this.workoutScheduleCount = workoutScheduleCount;
        this.attendanceCount = attendanceCount;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
    }
    
    // Getters
    public String getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public UserRole getRole() { return role; }
    public MemberId getMemberId() { return memberId; }
    public LocalDate getRegistrationDate() { return registrationDate; }
    public String getSubscriptionId() { return subscriptionId; }
    public SubscriptionStatus getSubscriptionStatus() { return subscriptionStatus; }
    public LocalDate getSubscriptionStartDate() { return subscriptionStartDate; }
    public LocalDate getSubscriptionEndDate() { return subscriptionEndDate; }
    public double getWeight() { return weight; }
    public double getBodyFat() { return bodyFat; }
    public int getWorkoutsCompleted() { return workoutsCompleted; }
    public int getWorkoutScheduleCount() { return workoutScheduleCount; }
    public int getAttendanceCount() { return attendanceCount; }
    public boolean isActive() { return isActive; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getLastModifiedAt() { return lastModifiedAt; }
    
    /**
     * Calculates the member's membership duration in days.
     * 
     * @return the number of days since registration
     */
    public long getMembershipDurationInDays() {
        return java.time.temporal.ChronoUnit.DAYS.between(registrationDate, LocalDate.now());
    }
    
    /**
     * Calculates the member's attendance rate.
     * 
     * @return the attendance rate as a percentage (0.0 to 100.0)
     */
    public double getAttendanceRate() {
        if (workoutScheduleCount == 0) {
            return 0.0;
        }
        return (double) attendanceCount / workoutScheduleCount * 100.0;
    }
    
    /**
     * Checks if the member has an active subscription.
     * 
     * @return true if the member has an active subscription, false otherwise
     */
    public boolean hasActiveSubscription() {
        return subscriptionStatus != null && subscriptionStatus.isActive();
    }
    
    @Override
    public String toString() {
        return String.format("MemberDTO{memberId=%s, username='%s', email='%s', subscription=%s, workouts=%d, attendance=%d}", 
                           memberId, username, email, subscriptionStatus, workoutsCompleted, attendanceCount);
    }
}
