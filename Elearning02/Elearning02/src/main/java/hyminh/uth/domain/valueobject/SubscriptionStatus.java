package hyminh.uth.domain.valueobject;

/**
 * Value object representing the status of a subscription.
 * This enum ensures type safety and provides a clear set of valid subscription statuses.
 * 
 * @author Gym Management System
 * @version 1.0
 */
public enum SubscriptionStatus {
    /**
     * Active subscription that is currently valid and can be used.
     */
    ACTIVE("Active", "Subscription is currently active and valid"),
    
    /**
     * Cancelled subscription that was terminated before expiration.
     */
    CANCELLED("Cancelled", "Subscription was cancelled before expiration"),
    
    /**
     * Expired subscription that has reached its end date.
     */
    EXPIRED("Expired", "Subscription has reached its end date"),
    
    /**
     * Suspended subscription that is temporarily inactive.
     */
    SUSPENDED("Suspended", "Subscription is temporarily suspended");
    
    private final String displayName;
    private final String description;
    
    /**
     * Constructs a SubscriptionStatus with the specified display name and description.
     * 
     * @param displayName the human-readable name of the status
     * @param description the description of the status
     */
    SubscriptionStatus(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
    
    /**
     * Returns the display name of the status.
     * 
     * @return the display name
     */
    public String getDisplayName() {
        return displayName;
    }
    
    /**
     * Returns the description of the status.
     * 
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Checks if this status represents an active subscription.
     * 
     * @return true if the status is active, false otherwise
     */
    public boolean isActive() {
        return this == ACTIVE;
    }
    
    /**
     * Checks if this status represents a cancelled subscription.
     * 
     * @return true if the status is cancelled, false otherwise
     */
    public boolean isCancelled() {
        return this == CANCELLED;
    }
    
    /**
     * Checks if this status represents an expired subscription.
     * 
     * @return true if the status is expired, false otherwise
     */
    public boolean isExpired() {
        return this == EXPIRED;
    }
    
    /**
     * Checks if this status represents a suspended subscription.
     * 
     * @return true if the status is suspended, false otherwise
     */
    public boolean isSuspended() {
        return this == SUSPENDED;
    }
    
    /**
     * Checks if this status allows gym access.
     * 
     * @return true if the status allows access, false otherwise
     */
    public boolean allowsAccess() {
        return this == ACTIVE;
    }
    
    @Override
    public String toString() {
        return displayName;
    }
}
