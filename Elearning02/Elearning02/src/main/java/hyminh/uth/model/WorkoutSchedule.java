package hyminh.uth.model;

import java.time.LocalDate;
import java.util.*;
import java.io.Serializable;

public class WorkoutSchedule implements Serializable {
    private String scheduleId;
    private String memberId;
    private String trainerId;
    private LocalDate date;
    private List<Exercise> exercises;
    private String notes;

    public WorkoutSchedule(String scheduleId, String memberId, String trainerId, LocalDate date) {
        this.scheduleId = scheduleId;
        this.memberId = memberId;
        this.trainerId = trainerId;
        this.date = date;
        this.exercises = new ArrayList<>();
        this.notes = "";
    }

    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
    }

    public String getScheduleId() { return scheduleId; }
    public String getMemberId() { return memberId; }
    public String getTrainerId() { return trainerId; }
    public LocalDate getDate() { return date; }
    public List<Exercise> getExercises() { return exercises; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    @Override
    public String toString() {
        return "WorkoutSchedule{" + "id='" + scheduleId + "', memberId='" + memberId +
                "', trainerId='" + trainerId + "', date=" + date +
                ", exercises=" + exercises.size() + "}";
    }
}