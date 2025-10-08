package hyminh.uth.domain.valueobject;

/**
 * Value object representing the type of a subscription plan.
 * This enum ensures type safety and provides a clear set of valid plan types.
 * 
 * @author Gym Management System
 * @version 1.0
 */
public enum PlanType {
    /**
     * Basic plan with minimal features and access.
     */
    BASIC("Basic", "Minimal features and basic gym access"),
    
    /**
     * Standard plan with moderate features and access.
     */
    STANDARD("Standard", "Moderate features and standard gym access"),
    
    /**
     * Premium plan with all features and full access.
     */
    PREMIUM("Premium", "All features and full gym access"),
    
    /**
     * VIP plan with exclusive features and priority access.
     */
    VIP("VIP", "Exclusive features and priority access");
    
    private final String displayName;
    private final String description;
    
    /**
     * Constructs a PlanType with the specified display name and description.
     * 
     * @param displayName the human-readable name of the plan type
     * @param description the description of the plan type
     */
    PlanType(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
    
    /**
     * Returns the display name of the plan type.
     * 
     * @return the display name
     */
    public String getDisplayName() {
        return displayName;
    }
    
    /**
     * Returns the description of the plan type.
     * 
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Checks if this plan type is basic.
     * 
     * @return true if the plan type is basic, false otherwise
     */
    public boolean isBasic() {
        return this == BASIC;
    }
    
    /**
     * Checks if this plan type is standard.
     * 
     * @return true if the plan type is standard, false otherwise
     */
    public boolean isStandard() {
        return this == STANDARD;
    }
    
    /**
     * Checks if this plan type is premium.
     * 
     * @return true if the plan type is premium, false otherwise
     */
    public boolean isPremium() {
        return this == PREMIUM;
    }
    
    /**
     * Checks if this plan type is VIP.
     * 
     * @return true if the plan type is VIP, false otherwise
     */
    public boolean isVIP() {
        return this == VIP;
    }
    
    /**
     * Gets the hierarchy level of this plan type (higher number = more features).
     * 
     * @return the hierarchy level
     */
    public int getHierarchyLevel() {
        switch (this) {
            case BASIC: return 1;
            case STANDARD: return 2;
            case PREMIUM: return 3;
            case VIP: return 4;
            default: return 0;
        }
    }
    
    /**
     * Checks if this plan type has more features than another plan type.
     * 
     * @param other the other plan type to compare
     * @return true if this plan type has more features, false otherwise
     */
    public boolean hasMoreFeaturesThan(PlanType other) {
        if (other == null) {
            throw new IllegalArgumentException("Other plan type cannot be null");
        }
        return this.getHierarchyLevel() > other.getHierarchyLevel();
    }
    
    @Override
    public String toString() {
        return displayName;
    }
}
