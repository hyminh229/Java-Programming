package hyminh.uth.domain.valueobject;

/**
 * Value object representing a trainer's specialization area.
 * This enum ensures type safety and provides a clear set of valid specializations.
 * 
 * @author Gym Management System
 * @version 1.0
 */
public enum Specialization {
    /**
     * General fitness training for all levels.
     */
    GENERAL("General Fitness", "General fitness training for all levels"),
    
    /**
     * Strength training focused on building muscle and power.
     */
    STRENGTH("Strength Training", "Strength training focused on building muscle and power"),
    
    /**
     * Cardiovascular training for endurance and heart health.
     */
    CARDIO("Cardiovascular Training", "Cardiovascular training for endurance and heart health"),
    
    /**
     * Weight loss and fat burning programs.
     */
    WEIGHT_LOSS("Weight Loss", "Weight loss and fat burning programs"),
    
    /**
     * Muscle building and hypertrophy programs.
     */
    MUSCLE_BUILDING("Muscle Building", "Muscle building and hypertrophy programs"),
    
    /**
     * Functional training for everyday movement patterns.
     */
    FUNCTIONAL("Functional Training", "Functional training for everyday movement patterns"),
    
    /**
     * Sports-specific training for athletes.
     */
    SPORTS("Sports Training", "Sports-specific training for athletes"),
    
    /**
     * Rehabilitation and injury recovery training.
     */
    REHABILITATION("Rehabilitation", "Rehabilitation and injury recovery training"),
    
    /**
     * Senior fitness and age-appropriate training.
     */
    SENIOR("Senior Fitness", "Senior fitness and age-appropriate training"),
    
    /**
     * Prenatal and postnatal fitness training.
     */
    PRENATAL("Prenatal/Postnatal", "Prenatal and postnatal fitness training");
    
    private final String displayName;
    private final String description;
    
    /**
     * Constructs a Specialization with the specified display name and description.
     * 
     * @param displayName the human-readable name of the specialization
     * @param description the description of the specialization
     */
    Specialization(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
    
    /**
     * Returns the display name of the specialization.
     * 
     * @return the display name
     */
    public String getDisplayName() {
        return displayName;
    }
    
    /**
     * Returns the description of the specialization.
     * 
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Checks if this specialization is general fitness.
     * 
     * @return true if the specialization is general, false otherwise
     */
    public boolean isGeneral() {
        return this == GENERAL;
    }
    
    /**
     * Checks if this specialization is strength-focused.
     * 
     * @return true if the specialization is strength-focused, false otherwise
     */
    public boolean isStrengthFocused() {
        return this == STRENGTH || this == MUSCLE_BUILDING;
    }
    
    /**
     * Checks if this specialization is cardio-focused.
     * 
     * @return true if the specialization is cardio-focused, false otherwise
     */
    public boolean isCardioFocused() {
        return this == CARDIO || this == WEIGHT_LOSS;
    }
    
    /**
     * Checks if this specialization is rehabilitation-focused.
     * 
     * @return true if the specialization is rehabilitation-focused, false otherwise
     */
    public boolean isRehabilitationFocused() {
        return this == REHABILITATION || this == SENIOR;
    }
    
    /**
     * Checks if this specialization is sports-focused.
     * 
     * @return true if the specialization is sports-focused, false otherwise
     */
    public boolean isSportsFocused() {
        return this == SPORTS || this == FUNCTIONAL;
    }
    
    /**
     * Checks if this specialization is suitable for beginners.
     * 
     * @return true if the specialization is suitable for beginners, false otherwise
     */
    public boolean isSuitableForBeginners() {
        return this == GENERAL || this == FUNCTIONAL || this == CARDIO;
    }
    
    /**
     * Checks if this specialization is suitable for advanced users.
     * 
     * @return true if the specialization is suitable for advanced users, false otherwise
     */
    public boolean isSuitableForAdvanced() {
        return this == STRENGTH || this == MUSCLE_BUILDING || this == SPORTS;
    }
    
    /**
     * Checks if this specialization requires special certifications.
     * 
     * @return true if the specialization requires special certifications, false otherwise
     */
    public boolean requiresSpecialCertification() {
        return this == REHABILITATION || this == PRENATAL || this == SENIOR;
    }
    
    @Override
    public String toString() {
        return displayName;
    }
}
