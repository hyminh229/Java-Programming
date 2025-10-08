package hyminh.uth.domain.repository.impl;

import hyminh.uth.domain.entity.Exercise;
import hyminh.uth.domain.repository.ExerciseRepository;
import hyminh.uth.domain.valueobject.ExerciseType;
import hyminh.uth.domain.valueobject.DifficultyLevel;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Triển khai trong bộ nhớ của ExerciseRepository.
 * Triển khai này lưu trữ exercises trong bộ nhớ sử dụng cấu trúc dữ liệu đồng thời.
 * 
 * @author Gym Management System
 * @version 1.0
 */
public class InMemoryExerciseRepository implements ExerciseRepository {
    
    private final Map<String, Exercise> exercises = new ConcurrentHashMap<>();
    
    @Override
    public Exercise save(Exercise exercise) {
        if (exercise == null) {
            throw new IllegalArgumentException("Exercise cannot be null");
        }
        exercises.put(exercise.getExerciseId(), exercise);
        return exercise;
    }
    
    @Override
    public Optional<Exercise> findById(String exerciseId) {
        if (exerciseId == null || exerciseId.trim().isEmpty()) {
            throw new IllegalArgumentException("Exercise ID cannot be null or empty");
        }
        return Optional.ofNullable(exercises.get(exerciseId));
    }
    
    @Override
    public List<Exercise> findByType(ExerciseType type) {
        if (type == null) {
            throw new IllegalArgumentException("Type cannot be null");
        }
        return exercises.values().stream()
                .filter(exercise -> exercise.getType() == type)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Exercise> findByDifficulty(DifficultyLevel difficulty) {
        if (difficulty == null) {
            throw new IllegalArgumentException("Difficulty cannot be null");
        }
        return exercises.values().stream()
                .filter(exercise -> exercise.getDifficulty() == difficulty)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Exercise> findSuitableFor(DifficultyLevel difficulty) {
        if (difficulty == null) {
            throw new IllegalArgumentException("Difficulty cannot be null");
        }
        return exercises.values().stream()
                .filter(exercise -> exercise.isSuitableFor(difficulty))
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Exercise> findByTargetMuscle(String muscleGroup) {
        if (muscleGroup == null || muscleGroup.trim().isEmpty()) {
            throw new IllegalArgumentException("Muscle group cannot be null or empty");
        }
        return exercises.values().stream()
                .filter(exercise -> exercise.targetsMuscleGroup(muscleGroup))
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Exercise> findByEquipment(String equipment) {
        if (equipment == null || equipment.trim().isEmpty()) {
            throw new IllegalArgumentException("Equipment cannot be null or empty");
        }
        return exercises.values().stream()
                .filter(exercise -> exercise.requiresEquipment(equipment))
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Exercise> findActiveExercises() {
        return exercises.values().stream()
                .filter(Exercise::isActive)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Exercise> findInactiveExercises() {
        return exercises.values().stream()
                .filter(exercise -> !exercise.isActive())
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Exercise> findAll() {
        return new ArrayList<>(exercises.values());
    }
    
    @Override
    public List<Exercise> searchByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        String searchTerm = name.toLowerCase();
        return exercises.values().stream()
                .filter(exercise -> exercise.getName().toLowerCase().contains(searchTerm))
                .collect(Collectors.toList());
    }
    
    @Override
    public boolean existsById(String exerciseId) {
        if (exerciseId == null || exerciseId.trim().isEmpty()) {
            throw new IllegalArgumentException("Exercise ID cannot be null or empty");
        }
        return exercises.containsKey(exerciseId);
    }
    
    @Override
    public boolean deleteById(String exerciseId) {
        if (exerciseId == null || exerciseId.trim().isEmpty()) {
            throw new IllegalArgumentException("Exercise ID cannot be null or empty");
        }
        return exercises.remove(exerciseId) != null;
    }
    
    @Override
    public long count() {
        return exercises.size();
    }
    
    @Override
    public long countByType(ExerciseType type) {
        if (type == null) {
            throw new IllegalArgumentException("Type cannot be null");
        }
        return exercises.values().stream()
                .filter(exercise -> exercise.getType() == type)
                .count();
    }
    
    @Override
    public long countByDifficulty(DifficultyLevel difficulty) {
        if (difficulty == null) {
            throw new IllegalArgumentException("Difficulty cannot be null");
        }
        return exercises.values().stream()
                .filter(exercise -> exercise.getDifficulty() == difficulty)
                .count();
    }
    
    @Override
    public long countActiveExercises() {
        return exercises.values().stream()
                .filter(Exercise::isActive)
                .count();
    }
    
    @Override
    public long countByTargetMuscle(String muscleGroup) {
        if (muscleGroup == null || muscleGroup.trim().isEmpty()) {
            throw new IllegalArgumentException("Muscle group cannot be null or empty");
        }
        return exercises.values().stream()
                .filter(exercise -> exercise.targetsMuscleGroup(muscleGroup))
                .count();
    }
}
