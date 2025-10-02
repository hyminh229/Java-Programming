package hyminh.uth.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class DataManager {
    private Gson gson;

    public DataManager() {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .registerTypeAdapter(LocalTime.class, new LocalTimeAdapter())
                .setPrettyPrinting()
                .create();
    }

    public void saveToJSON(String filename, Object data) {
        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            System.err.println("Error saving to JSON: " + e.getMessage());
        }
    }

    public <T> T loadFromJSON(String filename, Class<T> classType) {
        try (FileReader reader = new FileReader(filename)) {
            return gson.fromJson(reader, classType);
        } catch (IOException e) {
            System.err.println("Error loading from JSON: " + e.getMessage());
            return null;
        }
    }

    public void exportToCSV(String filename, List<String[]> data) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (String[] row : data) {
                writer.println(String.join(",", row));
            }
        } catch (IOException e) {
            System.err.println("Error exporting to CSV: " + e.getMessage());
        }
    }
}