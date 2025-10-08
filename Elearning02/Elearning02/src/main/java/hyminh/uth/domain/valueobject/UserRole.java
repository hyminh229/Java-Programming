package hyminh.uth.domain.valueobject;

/**
 * Value object representing the role of a user in the gym management system.
 * This enum ensures type safety and provides a clear set of valid user roles.
 * 
 * @author Gym Management System
 * @version 1.0
 */
public enum UserRole {
    /**
     * Administrator role with full system access and management capabilities.
     */
    ADMIN("Administrator", "Full system access and management capabilities"),
    
    /**
     * Trainer role with member management and workout scheduling capabilities.
     */
    TRAINER("Trainer", "Member management and workout scheduling capabilities"),
    
    /**
     * Member role with basic gym access and personal progress tracking.
     */
    MEMBER("Member", "Basic gym access and personal progress tracking");
    
    private final String displayName;
    private final String description;
    
    /**
     * Constructs a UserRole with the specified display name and description.
     * 
     * @param displayName the human-readable name of the role
     * @param description the description of the role's capabilities
     */
    UserRole(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
    
    /**
     * Returns the display name of the role.
     * 
     * @return the display name
     */
    public String getDisplayName() {
        return displayName;
    }
    
    /**
     * Returns the description of the role.
     * 
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Checks if this role has administrative privileges.
     * 
     * @return true if the role has admin privileges, false otherwise
     */
    public boolean isAdmin() {
        return this == ADMIN;
    }
    
    /**
     * Checks if this role has trainer privileges.
     * 
     * @return true if the role has trainer privileges, false otherwise
     */
    public boolean isTrainer() {
        return this == TRAINER;
    }
    
    /**
     * Checks if this role has member privileges.
     * 
     * @return true if the role has member privileges, false otherwise
     */
    public boolean isMember() {
        return this == MEMBER;
    }
    
    @Override
    public String toString() {
        return displayName;
    }
}
