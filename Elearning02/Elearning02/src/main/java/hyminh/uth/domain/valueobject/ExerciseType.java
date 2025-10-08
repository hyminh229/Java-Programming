package hyminh.uth.domain.valueobject;

/**
 * Value object representing the type of an exercise.
 * This enum ensures type safety and provides a clear set of valid exercise types.
 * 
 * @author Gym Management System
 * @version 1.0
 */
public enum ExerciseType {
    /**
     * Strength training exercises focused on building muscle and power.
     */
    STRENGTH("Strength Training", "Exercises focused on building muscle and power"),
    
    /**
     * Cardiovascular exercises for endurance and heart health.
     */
    CARDIO("Cardiovascular", "Exercises for endurance and heart health"),
    
    /**
     * Flexibility and stretching exercises.
     */
    FLEXIBILITY("Flexibility", "Exercises for flexibility and stretching"),
    
    /**
     * Functional movement exercises for everyday activities.
     */
    FUNCTIONAL("Functional", "Exercises for functional movement patterns"),
    
    /**
     * Balance and stability exercises.
     */
    BALANCE("Balance", "Exercises for balance and stability"),
    
    /**
     * Core strengthening exercises.
     */
    CORE("Core", "Exercises for core strengthening"),
    
    /**
     * Plyometric exercises for explosive power.
     */
    PLYOMETRIC("Plyometric", "Exercises for explosive power and speed"),
    
    /**
     * Isometric exercises for static strength.
     */
    ISOMETRIC("Isometric", "Exercises for static strength and endurance"),
    
    /**
     * Compound exercises that work multiple muscle groups.
     */
    COMPOUND("Compound", "Exercises that work multiple muscle groups"),
    
    /**
     * Isolation exercises that target specific muscles.
     */
    ISOLATION("Isolation", "Exercises that target specific muscles");
    
    private final String displayName;
    private final String description;
    
    /**
     * Constructs an ExerciseType with the specified display name and description.
     * 
     * @param displayName the human-readable name of the exercise type
     * @param description the description of the exercise type
     */
    ExerciseType(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
    
    /**
     * Returns the display name of the exercise type.
     * 
     * @return the display name
     */
    public String getDisplayName() {
        return displayName;
    }
    
    /**
     * Returns the description of the exercise type.
     * 
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Checks if this exercise type is strength-focused.
     * 
     * @return true if the exercise type is strength-focused, false otherwise
     */
    public boolean isStrengthFocused() {
        return this == STRENGTH || this == COMPOUND || this == ISOLATION;
    }
    
    /**
     * Checks if this exercise type is cardio-focused.
     * 
     * @return true if the exercise type is cardio-focused, false otherwise
     */
    public boolean isCardioFocused() {
        return this == CARDIO || this == PLYOMETRIC;
    }
    
    /**
     * Checks if this exercise type is flexibility-focused.
     * 
     * @return true if the exercise type is flexibility-focused, false otherwise
     */
    public boolean isFlexibilityFocused() {
        return this == FLEXIBILITY || this == BALANCE;
    }
    
    /**
     * Checks if this exercise type is functional-focused.
     * 
     * @return true if the exercise type is functional-focused, false otherwise
     */
    public boolean isFunctionalFocused() {
        return this == FUNCTIONAL || this == CORE;
    }
    
    /**
     * Checks if this exercise type is suitable for beginners.
     * 
     * @return true if the exercise type is suitable for beginners, false otherwise
     */
    public boolean isSuitableForBeginners() {
        return this == FLEXIBILITY || this == BALANCE || this == CORE;
    }
    
    /**
     * Checks if this exercise type is suitable for advanced users.
     * 
     * @return true if the exercise type is suitable for advanced users, false otherwise
     */
    public boolean isSuitableForAdvanced() {
        return this == PLYOMETRIC || this == ISOMETRIC || this == COMPOUND;
    }
    
    /**
     * Checks if this exercise type requires equipment.
     * 
     * @return true if the exercise type typically requires equipment, false otherwise
     */
    public boolean requiresEquipment() {
        return this == STRENGTH || this == COMPOUND || this == ISOLATION;
    }
    
    /**
     * Checks if this exercise type can be done at home.
     * 
     * @return true if the exercise type can be done at home, false otherwise
     */
    public boolean canBeDoneAtHome() {
        return this == FLEXIBILITY || this == BALANCE || this == CORE || 
               this == FUNCTIONAL || this == ISOMETRIC;
    }
    
    @Override
    public String toString() {
        return displayName;
    }
}
