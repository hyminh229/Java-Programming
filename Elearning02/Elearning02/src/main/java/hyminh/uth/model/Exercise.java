package hyminh.uth.model;

import java.io.Serializable;

public class Exercise implements Serializable {
    private String exerciseId;
    private String name;
    private String type;
    private int sets;
    private int reps;
    private String description;

    public Exercise(String exerciseId, String name, String type, int sets,
                    int reps, String description) {
        this.exerciseId = exerciseId;
        this.name = name;
        this.type = type;
        this.sets = sets;
        this.reps = reps;
        this.description = description;
    }

    public String getExerciseId() { return exerciseId; }
    public String getName() { return name; }
    public String getType() { return type; }
    public int getSets() { return sets; }
    public int getReps() { return reps; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return name + " (" + type + "): " + sets + " sets x " + reps + " reps";
    }
}
