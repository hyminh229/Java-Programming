package hyminh.uth.domain.entity;

import hyminh.uth.domain.valueobject.ExerciseType;
import hyminh.uth.domain.valueobject.DifficultyLevel;
import java.time.Duration;
import java.util.Objects;

/**
 * Exercise entity representing a workout exercise with all its properties and business logic.
 * This class encapsulates exercise-related data and behaviors.
 * 
 * @author Gym Management System
 * @version 1.0
 */
public class Exercise {
    
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
    private boolean isActive;
    
    /**
     * Constructs a new Exercise with the specified parameters.
     * 
     * @param exerciseId the unique exercise identifier
     * @param name the name of the exercise
     * @param type the type of exercise
     * @param difficulty the difficulty level
     * @param description the description of the exercise
     * @param instructions the step-by-step instructions
     * @param estimatedDuration the estimated duration to complete
     * @param defaultSets the default number of sets
     * @param defaultReps the default number of repetitions
     * @param defaultWeight the default weight to use
     * @param targetMuscles the target muscle groups
     * @param equipment the required equipment
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public Exercise(String exerciseId, String name, ExerciseType type, 
                   DifficultyLevel difficulty, String description, String instructions,
                   Duration estimatedDuration, int defaultSets, int defaultReps, 
                   double defaultWeight, String targetMuscles, String equipment) {
        if (exerciseId == null || exerciseId.trim().isEmpty()) {
            throw new IllegalArgumentException("Exercise ID cannot be null or empty");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Exercise name cannot be null or empty");
        }
        if (type == null) {
            throw new IllegalArgumentException("Exercise type cannot be null");
        }
        if (difficulty == null) {
            throw new IllegalArgumentException("Difficulty level cannot be null");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        if (instructions == null || instructions.trim().isEmpty()) {
            throw new IllegalArgumentException("Instructions cannot be null or empty");
        }
        if (estimatedDuration == null || estimatedDuration.isNegative()) {
            throw new IllegalArgumentException("Estimated duration cannot be null or negative");
        }
        if (defaultSets <= 0) {
            throw new IllegalArgumentException("Default sets must be positive");
        }
        if (defaultReps <= 0) {
            throw new IllegalArgumentException("Default reps must be positive");
        }
        if (defaultWeight < 0) {
            throw new IllegalArgumentException("Default weight cannot be negative");
        }
        if (targetMuscles == null || targetMuscles.trim().isEmpty()) {
            throw new IllegalArgumentException("Target muscles cannot be null or empty");
        }
        if (equipment == null || equipment.trim().isEmpty()) {
            throw new IllegalArgumentException("Equipment cannot be null or empty");
        }
        
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
        this.isActive = true;
    }
    
    /**
     * Creates a simple exercise with basic parameters.
     * 
     * @param exerciseId the unique exercise identifier
     * @param name the name of the exercise
     * @param type the type of exercise
     * @param difficulty the difficulty level
     * @param description the description of the exercise
     * @param targetMuscles the target muscle groups
     * @param equipment the required equipment
     * @return a new Exercise instance with default values
     */
    public static Exercise createSimple(String exerciseId, String name, ExerciseType type,
                                      DifficultyLevel difficulty, String description,
                                      String targetMuscles, String equipment) {
        return new Exercise(exerciseId, name, type, difficulty, description,
                          "Follow proper form and technique", Duration.ofMinutes(5),
                          3, 10, 0.0, targetMuscles, equipment);
    }
    
    /**
     * Activates the exercise.
     */
    public void activate() {
        this.isActive = true;
    }
    
    /**
     * Deactivates the exercise.
     */
    public void deactivate() {
        this.isActive = false;
    }
    
    /**
     * Checks if the exercise is suitable for a specific difficulty level.
     * 
     * @param userDifficulty the user's difficulty level
     * @return true if the exercise is suitable, false otherwise
     */
    public boolean isSuitableFor(DifficultyLevel userDifficulty) {
        if (userDifficulty == null) {
            throw new IllegalArgumentException("User difficulty cannot be null");
        }
        return userDifficulty.getLevel() >= this.difficulty.getLevel();
    }
    
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
     * Checks if the exercise requires specific equipment.
     * 
     * @param requiredEquipment the equipment to check
     * @return true if the exercise requires the equipment, false otherwise
     */
    public boolean requiresEquipment(String requiredEquipment) {
        if (requiredEquipment == null || requiredEquipment.trim().isEmpty()) {
            return false;
        }
        return equipment.toLowerCase().contains(requiredEquipment.toLowerCase());
    }
    
    /**
     * Checks if the exercise targets specific muscle groups.
     * 
     * @param muscleGroup the muscle group to check
     * @return true if the exercise targets the muscle group, false otherwise
     */
    public boolean targetsMuscleGroup(String muscleGroup) {
        if (muscleGroup == null || muscleGroup.trim().isEmpty()) {
            return false;
        }
        return targetMuscles.toLowerCase().contains(muscleGroup.toLowerCase());
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
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Exercise exercise = (Exercise) obj;
        return Objects.equals(exerciseId, exercise.exerciseId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(exerciseId);
    }
    
    @Override
    public String toString() {
        return String.format("Exercise{id='%s', name='%s', type=%s, difficulty=%s, sets=%d, reps=%d}", 
                           exerciseId, name, type, difficulty, defaultSets, defaultReps);
    }
}
