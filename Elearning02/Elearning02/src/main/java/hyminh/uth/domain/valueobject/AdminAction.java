package hyminh.uth.domain.valueobject;

/**
 * Value object representing an admin action that can be performed.
 * This enum ensures type safety and provides a clear set of valid admin actions.
 * 
 * @author Gym Management System
 * @version 1.0
 */
public enum AdminAction {
    /**
     * Action to manage users (create, update, delete, deactivate).
     */
    MANAGE_USERS("Manage Users", "Create, update, delete, and deactivate users"),
    
    /**
     * Action to manage subscriptions (create, update, cancel, renew).
     */
    MANAGE_SUBSCRIPTIONS("Manage Subscriptions", "Create, update, cancel, and renew subscriptions"),
    
    /**
     * Action to manage reports (generate, view, export reports).
     */
    MANAGE_REPORTS("Manage Reports", "Generate, view, and export system reports"),
    
    /**
     * Action to manage system settings (configure system parameters).
     */
    MANAGE_SYSTEM_SETTINGS("Manage System Settings", "Configure system parameters and settings");
    
    private final String displayName;
    private final String description;
    
    /**
     * Constructs an AdminAction with the specified display name and description.
     * 
     * @param displayName the human-readable name of the action
     * @param description the description of the action
     */
    AdminAction(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
    
    /**
     * Returns the display name of the action.
     * 
     * @return the display name
     */
    public String getDisplayName() {
        return displayName;
    }
    
    /**
     * Returns the description of the action.
     * 
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Checks if this action is related to user management.
     * 
     * @return true if the action is user-related, false otherwise
     */
    public boolean isUserRelated() {
        return this == MANAGE_USERS;
    }
    
    /**
     * Checks if this action is related to subscription management.
     * 
     * @return true if the action is subscription-related, false otherwise
     */
    public boolean isSubscriptionRelated() {
        return this == MANAGE_SUBSCRIPTIONS;
    }
    
    /**
     * Checks if this action is related to reporting.
     * 
     * @return true if the action is report-related, false otherwise
     */
    public boolean isReportRelated() {
        return this == MANAGE_REPORTS;
    }
    
    /**
     * Checks if this action is related to system configuration.
     * 
     * @return true if the action is system-related, false otherwise
     */
    public boolean isSystemRelated() {
        return this == MANAGE_SYSTEM_SETTINGS;
    }
    
    /**
     * Checks if this action requires high-level privileges.
     * 
     * @return true if the action requires high-level privileges, false otherwise
     */
    public boolean requiresHighLevelPrivileges() {
        return this == MANAGE_SYSTEM_SETTINGS || this == MANAGE_USERS;
    }
    
    @Override
    public String toString() {
        return displayName;
    }
}
