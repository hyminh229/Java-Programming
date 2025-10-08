package hyminh.uth.domain.valueobject;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Value object representing progress metrics for a member.
 * This class encapsulates all progress-related data and calculations.
 * 
 * @author Gym Management System
 * @version 1.0
 */
public final class ProgressMetrics {
    
    private final String memberId;
    private final LocalDate date;
    private final double weight;
    private final double bodyFat;
    private final int workoutsCompleted;
    private final String notes;
    
    /**
     * Constructs a new ProgressMetrics with default values.
     * 
     * @param memberId the member ID
     * @param date the date of the progress record
     */
    public ProgressMetrics(String memberId, LocalDate date) {
        this(memberId, date, 0.0, 0.0, 0, "");
    }
    
    /**
     * Constructs a new ProgressMetrics with the specified values.
     * 
     * @param memberId the member ID
     * @param date the date of the progress record
     * @param weight the weight in kilograms
     * @param bodyFat the body fat percentage
     * @param workoutsCompleted the number of workouts completed
     * @param notes additional notes
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public ProgressMetrics(String memberId, LocalDate date, double weight, 
                          double bodyFat, int workoutsCompleted, String notes) {
        if (memberId == null || memberId.trim().isEmpty()) {
            throw new IllegalArgumentException("Member ID cannot be null or empty");
        }
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        if (weight < 0) {
            throw new IllegalArgumentException("Weight cannot be negative");
        }
        if (bodyFat < 0 || bodyFat > 100) {
            throw new IllegalArgumentException("Body fat percentage must be between 0 and 100");
        }
        if (workoutsCompleted < 0) {
            throw new IllegalArgumentException("Workouts completed cannot be negative");
        }
        if (notes == null) {
            throw new IllegalArgumentException("Notes cannot be null");
        }
        
        this.memberId = memberId;
        this.date = date;
        this.weight = weight;
        this.bodyFat = bodyFat;
        this.workoutsCompleted = workoutsCompleted;
        this.notes = notes;
    }
    
    /**
     * Creates a new ProgressMetrics with updated weight and body fat.
     * 
     * @param newWeight the new weight
     * @param newBodyFat the new body fat percentage
     * @return a new ProgressMetrics instance with updated values
     */
    public ProgressMetrics updateMetrics(double newWeight, double newBodyFat) {
        return new ProgressMetrics(memberId, LocalDate.now(), newWeight, newBodyFat, workoutsCompleted, notes);
    }
    
    /**
     * Creates a new ProgressMetrics with incremented workout count.
     * 
     * @return a new ProgressMetrics instance with incremented workout count
     */
    public ProgressMetrics incrementWorkouts() {
        return new ProgressMetrics(memberId, date, weight, bodyFat, workoutsCompleted + 1, notes);
    }
    
    /**
     * Creates a new ProgressMetrics with updated notes.
     * 
     * @param newNotes the new notes
     * @return a new ProgressMetrics instance with updated notes
     */
    public ProgressMetrics updateNotes(String newNotes) {
        if (newNotes == null) {
            throw new IllegalArgumentException("Notes cannot be null");
        }
        return new ProgressMetrics(memberId, date, weight, bodyFat, workoutsCompleted, newNotes);
    }
    
    /**
     * Calculates the Body Mass Index (BMI).
     * 
     * @param heightInMeters the height in meters
     * @return the BMI value
     * @throws IllegalArgumentException if height is invalid
     */
    public double calculateBMI(double heightInMeters) {
        if (heightInMeters <= 0) {
            throw new IllegalArgumentException("Height must be positive");
        }
        return weight / (heightInMeters * heightInMeters);
    }
    
    /**
     * Determines the BMI category based on the calculated BMI.
     * 
     * @param heightInMeters the height in meters
     * @return the BMI category
     */
    public String getBMICategory(double heightInMeters) {
        double bmi = calculateBMI(heightInMeters);
        if (bmi < 18.5) return "Underweight";
        if (bmi < 25) return "Normal weight";
        if (bmi < 30) return "Overweight";
        return "Obese";
    }
    
    /**
     * Calculates the progress percentage based on a target weight.
     * 
     * @param targetWeight the target weight
     * @return the progress percentage (negative for weight gain, positive for weight loss)
     */
    public double calculateWeightProgress(double targetWeight) {
        if (targetWeight <= 0) {
            throw new IllegalArgumentException("Target weight must be positive");
        }
        return ((weight - targetWeight) / targetWeight) * 100;
    }
    
    // Getters
    public String getMemberId() { return memberId; }
    public LocalDate getDate() { return date; }
    public double getWeight() { return weight; }
    public double getBodyFat() { return bodyFat; }
    public int getWorkoutsCompleted() { return workoutsCompleted; }
    public String getNotes() { return notes; }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ProgressMetrics that = (ProgressMetrics) obj;
        return Double.compare(that.weight, weight) == 0 &&
               Double.compare(that.bodyFat, bodyFat) == 0 &&
               workoutsCompleted == that.workoutsCompleted &&
               Objects.equals(memberId, that.memberId) &&
               Objects.equals(date, that.date) &&
               Objects.equals(notes, that.notes);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(memberId, date, weight, bodyFat, workoutsCompleted, notes);
    }
    
    @Override
    public String toString() {
        return String.format("ProgressMetrics{memberId='%s', date=%s, weight=%.1fkg, bodyFat=%.1f%%, workouts=%d}", 
                           memberId, date, weight, bodyFat, workoutsCompleted);
    }
}
