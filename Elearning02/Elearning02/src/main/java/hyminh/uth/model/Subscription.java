package hyminh.uth.model;

import java.time.LocalDate;
import java.io.Serializable;

public class Subscription implements Serializable {
    private String subscriptionId;
    private SubscriptionPlan plan;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isActive;
    private double amount;

    public Subscription(String subscriptionId, SubscriptionPlan plan, LocalDate startDate) {
        this.subscriptionId = subscriptionId;
        this.plan = plan;
        this.startDate = startDate;
        this.endDate = startDate.plusMonths(plan.getDurationMonths());
        this.isActive = true;
        this.amount = plan.getPrice();
    }

    public boolean isExpired() {
        return LocalDate.now().isAfter(endDate);
    }

    public String getStatus() {
        if (isExpired()) {
            return "EXPIRED";
        }
        return isActive ? "ACTIVE" : "INACTIVE";
    }

    public String getSubscriptionId() { return subscriptionId; }
    public SubscriptionPlan getPlan() { return plan; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public double getAmount() { return amount; }

    @Override
    public String toString() {
        return "Subscription{" + "id='" + subscriptionId + "', plan='" + plan.getPlanName() +
                "', startDate=" + startDate + ", endDate=" + endDate + ", status=" + getStatus() + "}";
    }
}