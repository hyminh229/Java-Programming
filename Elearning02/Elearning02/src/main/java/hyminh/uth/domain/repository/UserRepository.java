package hyminh.uth.domain.repository;

import hyminh.uth.domain.entity.User;
import hyminh.uth.domain.valueobject.UserRole;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing User entities.
 * This interface defines the contract for user data access operations.
 * 
 * @author Gym Management System
 * @version 1.0
 */
public interface UserRepository {
    
    /**
     * Saves a user to the repository.
     * 
     * @param user the user to save
     * @return the saved user
     * @throws IllegalArgumentException if user is null
     */
    User save(User user);
    
    /**
     * Finds a user by their ID.
     * 
     * @param userId the user ID to search for
     * @return an Optional containing the user if found, empty otherwise
     * @throws IllegalArgumentException if userId is null or empty
     */
    Optional<User> findById(String userId);
    
    /**
     * Finds a user by their username.
     * 
     * @param username the username to search for
     * @return an Optional containing the user if found, empty otherwise
     * @throws IllegalArgumentException if username is null or empty
     */
    Optional<User> findByUsername(String username);
    
    /**
     * Finds a user by their email.
     * 
     * @param email the email to search for
     * @return an Optional containing the user if found, empty otherwise
     * @throws IllegalArgumentException if email is null or empty
     */
    Optional<User> findByEmail(String email);
    
    /**
     * Finds all users with the specified role.
     * 
     * @param role the role to filter by
     * @return a list of users with the specified role
     * @throws IllegalArgumentException if role is null
     */
    List<User> findByRole(UserRole role);
    
    /**
     * Finds all active users.
     * 
     * @return a list of all active users
     */
    List<User> findActiveUsers();
    
    /**
     * Finds all inactive users.
     * 
     * @return a list of all inactive users
     */
    List<User> findInactiveUsers();
    
    /**
     * Finds all users.
     * 
     * @return a list of all users
     */
    List<User> findAll();
    
    /**
     * Checks if a user exists with the specified ID.
     * 
     * @param userId the user ID to check
     * @return true if the user exists, false otherwise
     * @throws IllegalArgumentException if userId is null or empty
     */
    boolean existsById(String userId);
    
    /**
     * Checks if a user exists with the specified username.
     * 
     * @param username the username to check
     * @return true if the user exists, false otherwise
     * @throws IllegalArgumentException if username is null or empty
     */
    boolean existsByUsername(String username);
    
    /**
     * Checks if a user exists with the specified email.
     * 
     * @param email the email to check
     * @return true if the user exists, false otherwise
     * @throws IllegalArgumentException if email is null or empty
     */
    boolean existsByEmail(String email);
    
    /**
     * Deletes a user by their ID.
     * 
     * @param userId the user ID to delete
     * @return true if the user was deleted, false if not found
     * @throws IllegalArgumentException if userId is null or empty
     */
    boolean deleteById(String userId);
    
    /**
     * Counts the total number of users.
     * 
     * @return the total number of users
     */
    long count();
    
    /**
     * Counts the number of users with the specified role.
     * 
     * @param role the role to count
     * @return the number of users with the specified role
     * @throws IllegalArgumentException if role is null
     */
    long countByRole(UserRole role);
    
    /**
     * Counts the number of active users.
     * 
     * @return the number of active users
     */
    long countActiveUsers();
    
    /**
     * Counts the number of inactive users.
     * 
     * @return the number of inactive users
     */
    long countInactiveUsers();
}
