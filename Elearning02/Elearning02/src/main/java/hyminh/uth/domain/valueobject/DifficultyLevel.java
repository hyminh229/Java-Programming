package hyminh.uth.domain.valueobject;

/**
 * Value object representing the difficulty level of an exercise.
 * This enum ensures type safety and provides a clear set of valid difficulty levels.
 * 
 * @author Gym Management System
 * @version 1.0
 */
public enum DifficultyLevel {
    /**
     * Beginner level - suitable for those new to exercise.
     */
    BEGINNER("Beginner", "Suitable for those new to exercise", 1),
    
    /**
     * Novice level - basic exercises with some experience.
     */
    NOVICE("Novice", "Basic exercises with some experience", 2),
    
    /**
     * Intermediate level - moderate difficulty exercises.
     */
    INTERMEDIATE("Intermediate", "Moderate difficulty exercises", 3),
    
    /**
     * Advanced level - challenging exercises for experienced users.
     */
    ADVANCED("Advanced", "Challenging exercises for experienced users", 4),
    
    /**
     * Expert level - very difficult exercises for highly trained individuals.
     */
    EXPERT("Expert", "Very difficult exercises for highly trained individuals", 5);
    
    private final String displayName;
    private final String description;
    private final int level;
    
    /**
     * Constructs a DifficultyLevel with the specified parameters.
     * 
     * @param displayName the human-readable name of the difficulty level
     * @param description the description of the difficulty level
     * @param level the numeric level (1-5)
     */
    DifficultyLevel(String displayName, String description, int level) {
        this.displayName = displayName;
        this.description = description;
        this.level = level;
    }
    
    /**
     * Returns the display name of the difficulty level.
     * 
     * @return the display name
     */
    public String getDisplayName() {
        return displayName;
    }
    
    /**
     * Returns the description of the difficulty level.
     * 
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Returns the numeric level of the difficulty.
     * 
     * @return the numeric level (1-5)
     */
    public int getLevel() {
        return level;
    }
    
    /**
     * Checks if this difficulty level is beginner.
     * 
     * @return true if the difficulty level is beginner, false otherwise
     */
    public boolean isBeginner() {
        return this == BEGINNER;
    }
    
    /**
     * Checks if this difficulty level is novice.
     * 
     * @return true if the difficulty level is novice, false otherwise
     */
    public boolean isNovice() {
        return this == NOVICE;
    }
    
    /**
     * Checks if this difficulty level is intermediate.
     * 
     * @return true if the difficulty level is intermediate, false otherwise
     */
    public boolean isIntermediate() {
        return this == INTERMEDIATE;
    }
    
    /**
     * Checks if this difficulty level is advanced.
     * 
     * @return true if the difficulty level is advanced, false otherwise
     */
    public boolean isAdvanced() {
        return this == ADVANCED;
    }
    
    /**
     * Checks if this difficulty level is expert.
     * 
     * @return true if the difficulty level is expert, false otherwise
     */
    public boolean isExpert() {
        return this == EXPERT;
    }
    
    /**
     * Checks if this difficulty level is suitable for beginners.
     * 
     * @return true if the difficulty level is suitable for beginners, false otherwise
     */
    public boolean isSuitableForBeginners() {
        return level <= 2;
    }
    
    /**
     * Checks if this difficulty level is suitable for experienced users.
     * 
     * @return true if the difficulty level is suitable for experienced users, false otherwise
     */
    public boolean isSuitableForExperienced() {
        return level >= 3;
    }
    
    /**
     * Checks if this difficulty level is higher than another level.
     * 
     * @param other the other difficulty level to compare
     * @return true if this level is higher, false otherwise
     */
    public boolean isHigherThan(DifficultyLevel other) {
        if (other == null) {
            throw new IllegalArgumentException("Other difficulty level cannot be null");
        }
        return this.level > other.level;
    }
    
    /**
     * Checks if this difficulty level is lower than another level.
     * 
     * @param other the other difficulty level to compare
     * @return true if this level is lower, false otherwise
     */
    public boolean isLowerThan(DifficultyLevel other) {
        if (other == null) {
            throw new IllegalArgumentException("Other difficulty level cannot be null");
        }
        return this.level < other.level;
    }
    
    /**
     * Gets the next difficulty level.
     * 
     * @return the next difficulty level, or null if this is the highest level
     */
    public DifficultyLevel getNextLevel() {
        switch (this) {
            case BEGINNER: return NOVICE;
            case NOVICE: return INTERMEDIATE;
            case INTERMEDIATE: return ADVANCED;
            case ADVANCED: return EXPERT;
            case EXPERT: return null;
            default: return null;
        }
    }
    
    /**
     * Gets the previous difficulty level.
     * 
     * @return the previous difficulty level, or null if this is the lowest level
     */
    public DifficultyLevel getPreviousLevel() {
        switch (this) {
            case BEGINNER: return null;
            case NOVICE: return BEGINNER;
            case INTERMEDIATE: return NOVICE;
            case ADVANCED: return INTERMEDIATE;
            case EXPERT: return ADVANCED;
            default: return null;
        }
    }
    
    @Override
    public String toString() {
        return displayName;
    }
}
