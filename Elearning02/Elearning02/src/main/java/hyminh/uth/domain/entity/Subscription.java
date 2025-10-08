package hyminh.uth.domain.entity;

import hyminh.uth.domain.valueobject.SubscriptionStatus;
import hyminh.uth.domain.valueobject.SubscriptionPlan;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Subscription entity representing a member's gym subscription.
 * This class encapsulates subscription-related business logic and state.
 * 
 * @author Gym Management System
 * @version 1.0
 */
public class Subscription {
    
    private final String subscriptionId;
    private final SubscriptionPlan plan;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final double amount;
    private SubscriptionStatus status;
    private final LocalDate createdAt;
    
    /**
     * Constructs a new Subscription with the specified parameters.
     * 
     * @param subscriptionId the unique subscription identifier
     * @param plan the subscription plan
     * @param startDate the start date of the subscription
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public Subscription(String subscriptionId, SubscriptionPlan plan, LocalDate startDate) {
        if (subscriptionId == null || subscriptionId.trim().isEmpty()) {
            throw new IllegalArgumentException("Subscription ID cannot be null or empty");
        }
        if (plan == null) {
            throw new IllegalArgumentException("Subscription plan cannot be null");
        }
        if (startDate == null) {
            throw new IllegalArgumentException("Start date cannot be null");
        }
        if (startDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Start date cannot be in the past");
        }
        
        this.subscriptionId = subscriptionId;
        this.plan = plan;
        this.startDate = startDate;
        this.endDate = startDate.plusMonths(plan.getDurationMonths());
        this.amount = plan.getPrice();
        this.status = SubscriptionStatus.ACTIVE;
        this.createdAt = LocalDate.now();
    }
    
    /**
     * Checks if the subscription is currently active.
     * 
     * @return true if the subscription is active, false otherwise
     */
    public boolean isActive() {
        return status == SubscriptionStatus.ACTIVE && !isExpired();
    }
    
    /**
     * Checks if the subscription has expired.
     * 
     * @return true if the subscription has expired, false otherwise
     */
    public boolean isExpired() {
        return LocalDate.now().isAfter(endDate);
    }
    
    /**
     * Checks if the subscription is about to expire within the specified days.
     * 
     * @param days the number of days to check
     * @return true if the subscription expires within the specified days, false otherwise
     */
    public boolean isExpiringWithin(int days) {
        if (days < 0) {
            throw new IllegalArgumentException("Days cannot be negative");
        }
        LocalDate expirationThreshold = LocalDate.now().plusDays(days);
        return !isExpired() && endDate.isBefore(expirationThreshold);
    }
    
    /**
     * Cancels the subscription.
     * 
     * @throws IllegalStateException if the subscription is already cancelled or expired
     */
    public void cancel() {
        if (status == SubscriptionStatus.CANCELLED) {
            throw new IllegalStateException("Subscription is already cancelled");
        }
        if (isExpired()) {
            throw new IllegalStateException("Cannot cancel an expired subscription");
        }
        this.status = SubscriptionStatus.CANCELLED;
    }
    
    /**
     * Renews the subscription for another period.
     * 
     * @param newStartDate the new start date for the renewal
     * @return a new Subscription instance for the renewal
     * @throws IllegalArgumentException if the new start date is invalid
     * @throws IllegalStateException if the subscription cannot be renewed
     */
    public Subscription renew(LocalDate newStartDate) {
        if (newStartDate == null) {
            throw new IllegalArgumentException("New start date cannot be null");
        }
        if (newStartDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("New start date cannot be in the past");
        }
        if (status == SubscriptionStatus.CANCELLED) {
            throw new IllegalStateException("Cannot renew a cancelled subscription");
        }
        
        return new Subscription(
            subscriptionId + "-RENEWAL-" + System.currentTimeMillis(),
            plan,
            newStartDate
        );
    }
    
    /**
     * Calculates the number of days remaining in the subscription.
     * 
     * @return the number of days remaining (0 if expired)
     */
    public long getDaysRemaining() {
        if (isExpired()) {
            return 0;
        }
        return java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), endDate);
    }
    
    /**
     * Calculates the subscription duration in days.
     * 
     * @return the total duration in days
     */
    public long getDurationInDays() {
        return java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate);
    }
    
    /**
     * Calculates the daily cost of the subscription.
     * 
     * @return the daily cost
     */
    public double getDailyCost() {
        return amount / getDurationInDays();
    }
    
    /**
     * Updates the subscription status based on current date.
     */
    public void updateStatus() {
        if (isExpired() && status == SubscriptionStatus.ACTIVE) {
            this.status = SubscriptionStatus.EXPIRED;
        }
    }
    
    // Getters
    public String getSubscriptionId() { return subscriptionId; }
    public SubscriptionPlan getPlan() { return plan; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public double getAmount() { return amount; }
    public SubscriptionStatus getStatus() { return status; }
    public LocalDate getCreatedAt() { return createdAt; }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Subscription that = (Subscription) obj;
        return Objects.equals(subscriptionId, that.subscriptionId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(subscriptionId);
    }
    
    @Override
    public String toString() {
        return String.format("Subscription{id='%s', plan='%s', startDate=%s, endDate=%s, status=%s, amount=%.2f}", 
                           subscriptionId, plan.getPlanName(), startDate, endDate, status, amount);
    }
}
