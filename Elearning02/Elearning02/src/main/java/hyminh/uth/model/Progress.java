package hyminh.uth.model;

import java.time.LocalDate;
import java.io.Serializable;

public class Progress implements Serializable {
    private String progressId;
    private String memberId;
    private LocalDate date;
    private double weight;
    private double bodyFat;
    private int workoutsCompleted;
    private String notes;

    public Progress(String progressId, String memberId, LocalDate date) {
        this.progressId = progressId;
        this.memberId = memberId;
        this.date = date;
        this.weight = 0.0;
        this.bodyFat = 0.0;
        this.workoutsCompleted = 0;
        this.notes = "";
    }

    public void updateMetrics(double weight, double bodyFat) {
        this.weight = weight;
        this.bodyFat = bodyFat;
        this.date = LocalDate.now();
    }

    public void incrementWorkouts() {
        this.workoutsCompleted++;
    }

    public String getProgressId() { return progressId; }
    public String getMemberId() { return memberId; }
    public double getWeight() { return weight; }
    public double getBodyFat() { return bodyFat; }
    public int getWorkoutsCompleted() { return workoutsCompleted; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    @Override
    public String toString() {
        return "Progress{" + "memberId='" + memberId + "', weight=" + weight +
                "kg, bodyFat=" + bodyFat + "%, workouts=" + workoutsCompleted + "}";
    }
}
