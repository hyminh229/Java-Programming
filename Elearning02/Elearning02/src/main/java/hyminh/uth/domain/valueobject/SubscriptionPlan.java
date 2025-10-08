package hyminh.uth.domain.valueobject;

import java.util.Objects;

/**
 * Value object representing a subscription plan.
 * This class encapsulates all plan-related data and business rules.
 * 
 * @author Gym Management System
 * @version 1.0
 */
public final class SubscriptionPlan {
    
    private final String planId;
    private final String planName;
    private final int durationMonths;
    private final double price;
    private final String description;
    private final PlanType planType;
    private final boolean includesPersonalTraining;
    private final boolean includesGroupClasses;
    private final boolean includesLockerAccess;
    
    /**
     * Constructs a new SubscriptionPlan with the specified parameters.
     * 
     * @param planId the unique plan identifier
     * @param planName the name of the plan
     * @param durationMonths the duration in months
     * @param price the price of the plan
     * @param description the description of the plan
     * @param planType the type of the plan
     * @param includesPersonalTraining whether the plan includes personal training
     * @param includesGroupClasses whether the plan includes group classes
     * @param includesLockerAccess whether the plan includes locker access
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public SubscriptionPlan(String planId, String planName, int durationMonths, 
                           double price, String description, PlanType planType,
                           boolean includesPersonalTraining, boolean includesGroupClasses, 
                           boolean includesLockerAccess) {
        if (planId == null || planId.trim().isEmpty()) {
            throw new IllegalArgumentException("Plan ID cannot be null or empty");
        }
        if (planName == null || planName.trim().isEmpty()) {
            throw new IllegalArgumentException("Plan name cannot be null or empty");
        }
        if (durationMonths <= 0) {
            throw new IllegalArgumentException("Duration must be positive");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if (description == null) {
            throw new IllegalArgumentException("Description cannot be null");
        }
        if (planType == null) {
            throw new IllegalArgumentException("Plan type cannot be null");
        }
        
        this.planId = planId;
        this.planName = planName;
        this.durationMonths = durationMonths;
        this.price = price;
        this.description = description;
        this.planType = planType;
        this.includesPersonalTraining = includesPersonalTraining;
        this.includesGroupClasses = includesGroupClasses;
        this.includesLockerAccess = includesLockerAccess;
    }
    
    /**
     * Creates a basic subscription plan with minimal features.
     * 
     * @param planId the unique plan identifier
     * @param planName the name of the plan
     * @param durationMonths the duration in months
     * @param price the price of the plan
     * @return a new basic SubscriptionPlan instance
     */
    public static SubscriptionPlan createBasic(String planId, String planName, 
                                             int durationMonths, double price) {
        return new SubscriptionPlan(planId, planName, durationMonths, price, 
                                  "Basic gym access", PlanType.BASIC, 
                                  false, false, false);
    }
    
    /**
     * Creates a premium subscription plan with all features.
     * 
     * @param planId the unique plan identifier
     * @param planName the name of the plan
     * @param durationMonths the duration in months
     * @param price the price of the plan
     * @return a new premium SubscriptionPlan instance
     */
    public static SubscriptionPlan createPremium(String planId, String planName, 
                                               int durationMonths, double price) {
        return new SubscriptionPlan(planId, planName, durationMonths, price, 
                                  "Premium gym access with all features", PlanType.PREMIUM, 
                                  true, true, true);
    }
    
    /**
     * Calculates the monthly cost of the plan.
     * 
     * @return the monthly cost
     */
    public double getMonthlyCost() {
        return price / durationMonths;
    }
    
    /**
     * Calculates the daily cost of the plan.
     * 
     * @return the daily cost
     */
    public double getDailyCost() {
        return price / (durationMonths * 30.0);
    }
    
    /**
     * Checks if this plan is more expensive than another plan.
     * 
     * @param other the other plan to compare
     * @return true if this plan is more expensive, false otherwise
     */
    public boolean isMoreExpensiveThan(SubscriptionPlan other) {
        if (other == null) {
            throw new IllegalArgumentException("Other plan cannot be null");
        }
        return this.price > other.price;
    }
    
    /**
     * Checks if this plan has the same features as another plan.
     * 
     * @param other the other plan to compare
     * @return true if the plans have the same features, false otherwise
     */
    public boolean hasSameFeaturesAs(SubscriptionPlan other) {
        if (other == null) {
            throw new IllegalArgumentException("Other plan cannot be null");
        }
        return this.includesPersonalTraining == other.includesPersonalTraining &&
               this.includesGroupClasses == other.includesGroupClasses &&
               this.includesLockerAccess == other.includesLockerAccess;
    }
    
    // Getters
    public String getPlanId() { return planId; }
    public String getPlanName() { return planName; }
    public int getDurationMonths() { return durationMonths; }
    public double getPrice() { return price; }
    public String getDescription() { return description; }
    public PlanType getPlanType() { return planType; }
    public boolean includesPersonalTraining() { return includesPersonalTraining; }
    public boolean includesGroupClasses() { return includesGroupClasses; }
    public boolean includesLockerAccess() { return includesLockerAccess; }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SubscriptionPlan that = (SubscriptionPlan) obj;
        return durationMonths == that.durationMonths &&
               Double.compare(that.price, price) == 0 &&
               includesPersonalTraining == that.includesPersonalTraining &&
               includesGroupClasses == that.includesGroupClasses &&
               includesLockerAccess == that.includesLockerAccess &&
               Objects.equals(planId, that.planId) &&
               Objects.equals(planName, that.planName) &&
               Objects.equals(description, that.description) &&
               planType == that.planType;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(planId, planName, durationMonths, price, description, planType, 
                          includesPersonalTraining, includesGroupClasses, includesLockerAccess);
    }
    
    @Override
    public String toString() {
        return String.format("SubscriptionPlan{id='%s', name='%s', duration=%d months, price=%.2f, type=%s}", 
                           planId, planName, durationMonths, price, planType);
    }
}
