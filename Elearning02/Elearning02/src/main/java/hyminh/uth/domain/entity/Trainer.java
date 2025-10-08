package hyminh.uth.domain.entity;

import hyminh.uth.domain.valueobject.Specialization;
import hyminh.uth.domain.valueobject.UserRole;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Trainer entity representing a gym trainer with member management capabilities.
 * This class encapsulates trainer-specific business logic and state.
 * 
 * @author Gym Management System
 * @version 1.0
 */
public class Trainer extends User {
    
    private final Specialization specialization;
    private final List<String> assignedMemberIds;
    private final LocalDateTime certificationDate;
    private int yearsOfExperience;
    private boolean isAvailable;
    private final List<String> workoutScheduleIds;
    
    /**
     * Constructs a new Trainer with the specified parameters.
     * 
     * @param userId the unique identifier for the user
     * @param username the username for login
     * @param password the password for authentication
     * @param email the email address
     * @param phone the phone number
     * @param specialization the trainer's specialization
     * @param yearsOfExperience the number of years of experience
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public Trainer(String userId, String username, String password, 
                   String email, String phone, Specialization specialization, 
                   int yearsOfExperience) {
        super(userId, username, password, email, phone, UserRole.TRAINER);
        
        if (specialization == null) {
            throw new IllegalArgumentException("Specialization cannot be null");
        }
        if (yearsOfExperience < 0) {
            throw new IllegalArgumentException("Years of experience cannot be negative");
        }
        
        this.specialization = specialization;
        this.assignedMemberIds = new ArrayList<>();
        this.certificationDate = LocalDateTime.now();
        this.yearsOfExperience = yearsOfExperience;
        this.isAvailable = true;
        this.workoutScheduleIds = new ArrayList<>();
    }
    
    /**
     * Assigns a member to this trainer.
     * 
     * @param memberId the member ID to assign
     * @throws IllegalArgumentException if memberId is null or empty
     * @throws IllegalStateException if trainer is not available
     */
    public void assignMember(String memberId) {
        if (memberId == null || memberId.trim().isEmpty()) {
            throw new IllegalArgumentException("Member ID cannot be null or empty");
        }
        if (!isAvailable) {
            throw new IllegalStateException("Trainer is not available for new assignments");
        }
        if (!assignedMemberIds.contains(memberId)) {
            assignedMemberIds.add(memberId);
        }
    }
    
    /**
     * Removes a member from this trainer's assignments.
     * 
     * @param memberId the member ID to remove
     */
    public void removeMember(String memberId) {
        assignedMemberIds.remove(memberId);
    }
    
    /**
     * Adds a workout schedule ID to the trainer's list.
     * 
     * @param scheduleId the workout schedule ID to add
     * @throws IllegalArgumentException if scheduleId is null or empty
     */
    public void addWorkoutSchedule(String scheduleId) {
        if (scheduleId == null || scheduleId.trim().isEmpty()) {
            throw new IllegalArgumentException("Schedule ID cannot be null or empty");
        }
        if (!workoutScheduleIds.contains(scheduleId)) {
            workoutScheduleIds.add(scheduleId);
        }
    }
    
    /**
     * Removes a workout schedule ID from the trainer's list.
     * 
     * @param scheduleId the workout schedule ID to remove
     */
    public void removeWorkoutSchedule(String scheduleId) {
        workoutScheduleIds.remove(scheduleId);
    }
    
    /**
     * Updates the trainer's years of experience.
     * 
     * @param newYearsOfExperience the new years of experience
     * @throws IllegalArgumentException if the new value is negative
     */
    public void updateExperience(int newYearsOfExperience) {
        if (newYearsOfExperience < 0) {
            throw new IllegalArgumentException("Years of experience cannot be negative");
        }
        this.yearsOfExperience = newYearsOfExperience;
    }
    
    /**
     * Sets the trainer's availability status.
     * 
     * @param available the availability status
     */
    public void setAvailability(boolean available) {
        this.isAvailable = available;
    }
    
    /**
     * Calculates the trainer's workload (number of assigned members).
     * 
     * @return the number of assigned members
     */
    public int getWorkload() {
        return assignedMemberIds.size();
    }
    
    /**
     * Calculates the trainer's schedule workload (number of workout schedules).
     * 
     * @return the number of workout schedules
     */
    public int getScheduleWorkload() {
        return workoutScheduleIds.size();
    }
    
    /**
     * Checks if the trainer can handle more members.
     * 
     * @param maxMembers the maximum number of members allowed
     * @return true if the trainer can handle more members, false otherwise
     */
    public boolean canHandleMoreMembers(int maxMembers) {
        return assignedMemberIds.size() < maxMembers;
    }
    
    /**
     * Checks if the trainer is qualified for a specific specialization.
     * 
     * @param requiredSpecialization the required specialization
     * @return true if the trainer is qualified, false otherwise
     */
    public boolean isQualifiedFor(Specialization requiredSpecialization) {
        if (requiredSpecialization == null) {
            throw new IllegalArgumentException("Required specialization cannot be null");
        }
        return this.specialization == requiredSpecialization || 
               this.specialization == Specialization.GENERAL;
    }
    
    /**
     * Calculates the trainer's experience level based on years of experience.
     * 
     * @return the experience level
     */
    public String getExperienceLevel() {
        if (yearsOfExperience < 2) return "Junior";
        if (yearsOfExperience < 5) return "Intermediate";
        if (yearsOfExperience < 10) return "Senior";
        return "Expert";
    }
    
    // Getters
    public Specialization getSpecialization() { return specialization; }
    public List<String> getAssignedMemberIds() { return Collections.unmodifiableList(assignedMemberIds); }
    public LocalDateTime getCertificationDate() { return certificationDate; }
    public int getYearsOfExperience() { return yearsOfExperience; }
    public boolean isAvailable() { return isAvailable; }
    public List<String> getWorkoutScheduleIds() { return Collections.unmodifiableList(workoutScheduleIds); }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;
        Trainer trainer = (Trainer) obj;
        return Objects.equals(specialization, trainer.specialization);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), specialization);
    }
    
    @Override
    public String toString() {
        return String.format("Trainer{userId='%s', username='%s', specialization=%s, experience=%d years, members=%d, available=%s}", 
                           getUserId(), getUsername(), specialization, yearsOfExperience, 
                           assignedMemberIds.size(), isAvailable);
    }
}
