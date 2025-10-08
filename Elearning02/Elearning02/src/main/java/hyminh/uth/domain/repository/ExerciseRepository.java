package hyminh.uth.domain.repository;

import hyminh.uth.domain.entity.Exercise;
import hyminh.uth.domain.valueobject.ExerciseType;
import hyminh.uth.domain.valueobject.DifficultyLevel;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing Exercise entities.
 * This interface defines the contract for exercise data access operations.
 * 
 * @author Gym Management System
 * @version 1.0
 */
public interface ExerciseRepository {
    
    /**
     * Saves an exercise to the repository.
     * 
     * @param exercise the exercise to save
     * @return the saved exercise
     * @throws IllegalArgumentException if exercise is null
     */
    Exercise save(Exercise exercise);
    
    /**
     * Finds an exercise by its ID.
     * 
     * @param exerciseId the exercise ID to search for
     * @return an Optional containing the exercise if found, empty otherwise
     * @throws IllegalArgumentException if exerciseId is null or empty
     */
    Optional<Exercise> findById(String exerciseId);
    
    /**
     * Finds all exercises with the specified type.
     * 
     * @param type the exercise type to filter by
     * @return a list of exercises with the specified type
     * @throws IllegalArgumentException if type is null
     */
    List<Exercise> findByType(ExerciseType type);
    
    /**
     * Finds all exercises with the specified difficulty level.
     * 
     * @param difficulty the difficulty level to filter by
     * @return a list of exercises with the specified difficulty
     * @throws IllegalArgumentException if difficulty is null
     */
    List<Exercise> findByDifficulty(DifficultyLevel difficulty);
    
    /**
     * Finds all exercises suitable for the specified difficulty level.
     * 
     * @param difficulty the difficulty level to filter by
     * @return a list of exercises suitable for the specified difficulty
     * @throws IllegalArgumentException if difficulty is null
     */
    List<Exercise> findSuitableFor(DifficultyLevel difficulty);
    
    /**
     * Finds all exercises that target the specified muscle group.
     * 
     * @param muscleGroup the muscle group to filter by
     * @return a list of exercises targeting the specified muscle group
     * @throws IllegalArgumentException if muscleGroup is null or empty
     */
    List<Exercise> findByTargetMuscle(String muscleGroup);
    
    /**
     * Finds all exercises that require the specified equipment.
     * 
     * @param equipment the equipment to filter by
     * @return a list of exercises requiring the specified equipment
     * @throws IllegalArgumentException if equipment is null or empty
     */
    List<Exercise> findByEquipment(String equipment);
    
    /**
     * Finds all active exercises.
     * 
     * @return a list of all active exercises
     */
    List<Exercise> findActiveExercises();
    
    /**
     * Finds all inactive exercises.
     * 
     * @return a list of all inactive exercises
     */
    List<Exercise> findInactiveExercises();
    
    /**
     * Finds all exercises.
     * 
     * @return a list of all exercises
     */
    List<Exercise> findAll();
    
    /**
     * Searches exercises by name (case-insensitive).
     * 
     * @param name the name to search for
     * @return a list of exercises matching the name
     * @throws IllegalArgumentException if name is null or empty
     */
    List<Exercise> searchByName(String name);
    
    /**
     * Checks if an exercise exists with the specified ID.
     * 
     * @param exerciseId the exercise ID to check
     * @return true if the exercise exists, false otherwise
     * @throws IllegalArgumentException if exerciseId is null or empty
     */
    boolean existsById(String exerciseId);
    
    /**
     * Deletes an exercise by its ID.
     * 
     * @param exerciseId the exercise ID to delete
     * @return true if the exercise was deleted, false if not found
     * @throws IllegalArgumentException if exerciseId is null or empty
     */
    boolean deleteById(String exerciseId);
    
    /**
     * Counts the total number of exercises.
     * 
     * @return the total number of exercises
     */
    long count();
    
    /**
     * Counts the number of exercises with the specified type.
     * 
     * @param type the exercise type to count
     * @return the number of exercises with the specified type
     * @throws IllegalArgumentException if type is null
     */
    long countByType(ExerciseType type);
    
    /**
     * Counts the number of exercises with the specified difficulty level.
     * 
     * @param difficulty the difficulty level to count
     * @return the number of exercises with the specified difficulty
     * @throws IllegalArgumentException if difficulty is null
     */
    long countByDifficulty(DifficultyLevel difficulty);
    
    /**
     * Counts the number of active exercises.
     * 
     * @return the number of active exercises
     */
    long countActiveExercises();
    
    /**
     * Counts the number of exercises that target the specified muscle group.
     * 
     * @param muscleGroup the muscle group to count
     * @return the number of exercises targeting the specified muscle group
     * @throws IllegalArgumentException if muscleGroup is null or empty
     */
    long countByTargetMuscle(String muscleGroup);
}
