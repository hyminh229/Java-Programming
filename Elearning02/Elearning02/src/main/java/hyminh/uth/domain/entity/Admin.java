package hyminh.uth.domain.entity;

import hyminh.uth.domain.valueobject.UserRole;
import hyminh.uth.domain.valueobject.AdminAction;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Admin entity representing a system administrator with full management capabilities.
 * This class encapsulates admin-specific business logic and state.
 * 
 * @author Gym Management System
 * @version 1.0
 */
public class Admin extends User {
    
    private final LocalDateTime adminSince;
    private final String adminLevel;
    private boolean canManageUsers;
    private boolean canManageSubscriptions;
    private boolean canManageReports;
    private boolean canManageSystemSettings;
    
    /**
     * Constructs a new Admin with the specified parameters.
     * 
     * @param userId the unique identifier for the user
     * @param username the username for login
     * @param password the password for authentication
     * @param email the email address
     * @param phone the phone number
     * @param adminLevel the level of admin privileges
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public Admin(String userId, String username, String password, 
                 String email, String phone, String adminLevel) {
        super(userId, username, password, email, phone, UserRole.ADMIN);
        
        if (adminLevel == null || adminLevel.trim().isEmpty()) {
            throw new IllegalArgumentException("Admin level cannot be null or empty");
        }
        
        this.adminSince = LocalDateTime.now();
        this.adminLevel = adminLevel;
        this.canManageUsers = true;
        this.canManageSubscriptions = true;
        this.canManageReports = true;
        this.canManageSystemSettings = true;
    }
    
    /**
     * Constructs a new Admin with custom permissions.
     * 
     * @param userId the unique identifier for the user
     * @param username the username for login
     * @param password the password for authentication
     * @param email the email address
     * @param phone the phone number
     * @param adminLevel the level of admin privileges
     * @param canManageUsers whether the admin can manage users
     * @param canManageSubscriptions whether the admin can manage subscriptions
     * @param canManageReports whether the admin can manage reports
     * @param canManageSystemSettings whether the admin can manage system settings
     */
    public Admin(String userId, String username, String password, 
                 String email, String phone, String adminLevel,
                 boolean canManageUsers, boolean canManageSubscriptions,
                 boolean canManageReports, boolean canManageSystemSettings) {
        super(userId, username, password, email, phone, UserRole.ADMIN);
        
        if (adminLevel == null || adminLevel.trim().isEmpty()) {
            throw new IllegalArgumentException("Admin level cannot be null or empty");
        }
        
        this.adminSince = LocalDateTime.now();
        this.adminLevel = adminLevel;
        this.canManageUsers = canManageUsers;
        this.canManageSubscriptions = canManageSubscriptions;
        this.canManageReports = canManageReports;
        this.canManageSystemSettings = canManageSystemSettings;
    }
    
    /**
     * Updates the admin's permissions.
     * 
     * @param canManageUsers whether the admin can manage users
     * @param canManageSubscriptions whether the admin can manage subscriptions
     * @param canManageReports whether the admin can manage reports
     * @param canManageSystemSettings whether the admin can manage system settings
     */
    public void updatePermissions(boolean canManageUsers, boolean canManageSubscriptions,
                                 boolean canManageReports, boolean canManageSystemSettings) {
        this.canManageUsers = canManageUsers;
        this.canManageSubscriptions = canManageSubscriptions;
        this.canManageReports = canManageReports;
        this.canManageSystemSettings = canManageSystemSettings;
    }
    
    /**
     * Checks if the admin has full privileges.
     * 
     * @return true if the admin has all privileges, false otherwise
     */
    public boolean hasFullPrivileges() {
        return canManageUsers && canManageSubscriptions && 
               canManageReports && canManageSystemSettings;
    }
    
    /**
     * Checks if the admin can perform a specific action.
     * 
     * @param action the action to check
     * @return true if the admin can perform the action, false otherwise
     */
    public boolean canPerformAction(AdminAction action) {
        if (action == null) {
            throw new IllegalArgumentException("Action cannot be null");
        }
        
        switch (action) {
            case MANAGE_USERS:
                return canManageUsers;
            case MANAGE_SUBSCRIPTIONS:
                return canManageSubscriptions;
            case MANAGE_REPORTS:
                return canManageReports;
            case MANAGE_SYSTEM_SETTINGS:
                return canManageSystemSettings;
            default:
                return false;
        }
    }
    
    /**
     * Calculates the admin's tenure in days.
     * 
     * @return the number of days since becoming admin
     */
    public long getTenureInDays() {
        return java.time.temporal.ChronoUnit.DAYS.between(adminSince, LocalDateTime.now());
    }
    
    /**
     * Checks if the admin is a senior admin (more than 1 year tenure).
     * 
     * @return true if the admin is senior, false otherwise
     */
    public boolean isSeniorAdmin() {
        return getTenureInDays() > 365;
    }
    
    /**
     * Checks if the admin is a junior admin (less than 6 months tenure).
     * 
     * @return true if the admin is junior, false otherwise
     */
    public boolean isJuniorAdmin() {
        return getTenureInDays() < 180;
    }
    
    // Getters
    public LocalDateTime getAdminSince() { return adminSince; }
    public String getAdminLevel() { return adminLevel; }
    public boolean canManageUsers() { return canManageUsers; }
    public boolean canManageSubscriptions() { return canManageSubscriptions; }
    public boolean canManageReports() { return canManageReports; }
    public boolean canManageSystemSettings() { return canManageSystemSettings; }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;
        Admin admin = (Admin) obj;
        return Objects.equals(adminLevel, admin.adminLevel);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), adminLevel);
    }
    
    @Override
    public String toString() {
        return String.format("Admin{userId='%s', username='%s', level='%s', tenure=%d days, fullPrivileges=%s}", 
                           getUserId(), getUsername(), adminLevel, getTenureInDays(), hasFullPrivileges());
    }
}
