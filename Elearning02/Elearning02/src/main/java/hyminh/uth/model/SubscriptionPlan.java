package hyminh.uth.model;

import java.io.Serializable;

public class SubscriptionPlan implements Serializable {
    private String planId;
    private String planName;
    private int durationMonths;
    private double price;
    private String description;

    public SubscriptionPlan(String planId, String planName, int durationMonths,
                            double price, String description) {
        this.planId = planId;
        this.planName = planName;
        this.durationMonths = durationMonths;
        this.price = price;
        this.description = description;
    }

    public String getPlanId() { return planId; }
    public String getPlanName() { return planName; }
    public int getDurationMonths() { return durationMonths; }
    public double getPrice() { return price; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return "Plan{" + "id='" + planId + "', name='" + planName +
                "', duration=" + durationMonths + " months, price=$" + price + "}";
    }
}