package hyminh.uth.domain.dto;

import hyminh.uth.domain.valueobject.ExerciseType;
import hyminh.uth.domain.valueobject.DifficultyLevel;
import java.time.Duration;

/**
 * Data Transfer Object for Exercise entities.
 * This class represents an exercise's data for transfer between layers.
 * 
 * @author Gym Management System
 * @version 1.0
 */
public class ExerciseDTO {
    
    private final String exerciseId;
    private final String name;
    private final ExerciseType type;
    private final DifficultyLevel difficulty;
    private final String description;
    private final String instructions;
    private final Duration estimatedDuration;
    private final int defaultSets;
    private final int defaultReps;
    private final double defaultWeight;
    private final String targetMuscles;
    private final String equipment;
    private final boolean isActive;
    
    /**
     * Constructs a new ExerciseDTO with the specified parameters.
     * 
     * @param exerciseId the exercise ID
     * @param name the exercise name
     * @param type the exercise type
     * @param difficulty the difficulty level
     * @param description the description
     * @param instructions the instructions
     * @param estimatedDuration the estimated duration
     * @param defaultSets the default number of sets
     * @param defaultReps the default number of reps
     * @param defaultWeight the default weight
     * @param targetMuscles the target muscles
     * @param equipment the required equipment
     * @param isActive whether the exercise is active
     */
    public ExerciseDTO(String exerciseId, String name, ExerciseType type, DifficultyLevel difficulty,
                       String description, String instructions, Duration estimatedDuration,
                       int defaultSets, int defaultReps, double defaultWeight,
                       String targetMuscles, String equipment, boolean isActive) {
        this.exerciseId = exerciseId;
        this.name = name;
        this.type = type;
        this.difficulty = difficulty;
        this.description = description;
        this.instructions = instructions;
        this.estimatedDuration = estimatedDuration;
        this.defaultSets = defaultSets;
        this.defaultReps = defaultReps;
        this.defaultWeight = defaultWeight;
        this.targetMuscles = targetMuscles;
        this.equipment = equipment;
        this.isActive = isActive;
    }
    
    // Getters
    public String getExerciseId() { return exerciseId; }
    public String getName() { return name; }
    public ExerciseType getType() { return type; }
    public DifficultyLevel getDifficulty() { return difficulty; }
    public String getDescription() { return description; }
    public String getInstructions() { return instructions; }
    public Duration getEstimatedDuration() { return estimatedDuration; }
    public int getDefaultSets() { return defaultSets; }
    public int getDefaultReps() { return defaultReps; }
    public double getDefaultWeight() { return defaultWeight; }
    public String getTargetMuscles() { return targetMuscles; }
    public String getEquipment() { return equipment; }
    public boolean isActive() { return isActive; }
    
    /**
     * Calculates the total estimated time for the exercise including rest periods.
     * 
     * @param restBetweenSets the rest time between sets
     * @return the total estimated time
     */
    public Duration calculateTotalTime(Duration restBetweenSets) {
        if (restBetweenSets == null || restBetweenSets.isNegative()) {
            throw new IllegalArgumentException("Rest time cannot be null or negative");
        }
        
        Duration exerciseTime = estimatedDuration.multipliedBy(defaultSets);
        Duration restTime = restBetweenSets.multipliedBy(Math.max(0, defaultSets - 1));
        return exerciseTime.plus(restTime);
    }
    
    /**
     * Calculates the total volume (sets × reps × weight) for the exercise.
     * 
     * @return the total volume
     */
    public double calculateTotalVolume() {
        return defaultSets * defaultReps * defaultWeight;
    }
    
    /**
     * Checks if the exercise is suitable for beginners.
     * 
     * @return true if the exercise is suitable for beginners, false otherwise
     */
    public boolean isSuitableForBeginners() {
        return difficulty.isSuitableForBeginners() && type.isSuitableForBeginners();
    }
    
    /**
     * Checks if the exercise is suitable for advanced users.
     * 
     * @return true if the exercise is suitable for advanced users, false otherwise
     */
    public boolean isSuitableForAdvanced() {
        return difficulty.isSuitableForExperienced() && type.isSuitableForAdvanced();
    }
    
    @Override
    public String toString() {
        return String.format("ExerciseDTO{id='%s', name='%s', type=%s, difficulty=%s, sets=%d, reps=%d}", 
                           exerciseId, name, type, difficulty, defaultSets, defaultReps);
    }
}
