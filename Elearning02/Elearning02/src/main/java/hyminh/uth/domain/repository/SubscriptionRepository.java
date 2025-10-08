package hyminh.uth.domain.repository;

import hyminh.uth.domain.entity.Subscription;
import hyminh.uth.domain.valueobject.SubscriptionStatus;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing Subscription entities.
 * This interface defines the contract for subscription data access operations.
 * 
 * @author Gym Management System
 * @version 1.0
 */
public interface SubscriptionRepository {
    
    /**
     * Saves a subscription to the repository.
     * 
     * @param subscription the subscription to save
     * @return the saved subscription
     * @throws IllegalArgumentException if subscription is null
     */
    Subscription save(Subscription subscription);
    
    /**
     * Finds a subscription by its ID.
     * 
     * @param subscriptionId the subscription ID to search for
     * @return an Optional containing the subscription if found, empty otherwise
     * @throws IllegalArgumentException if subscriptionId is null or empty
     */
    Optional<Subscription> findById(String subscriptionId);
    
    /**
     * Finds all subscriptions with the specified status.
     * 
     * @param status the status to filter by
     * @return a list of subscriptions with the specified status
     * @throws IllegalArgumentException if status is null
     */
    List<Subscription> findByStatus(SubscriptionStatus status);
    
    /**
     * Finds all active subscriptions.
     * 
     * @return a list of all active subscriptions
     */
    List<Subscription> findActiveSubscriptions();
    
    /**
     * Finds all expired subscriptions.
     * 
     * @return a list of all expired subscriptions
     */
    List<Subscription> findExpiredSubscriptions();
    
    /**
     * Finds all subscriptions that expire on or before the specified date.
     * 
     * @param date the date to filter by
     * @return a list of subscriptions expiring on or before the date
     * @throws IllegalArgumentException if date is null
     */
    List<Subscription> findExpiringBy(LocalDate date);
    
    /**
     * Finds all subscriptions that start on or after the specified date.
     * 
     * @param date the date to filter by
     * @return a list of subscriptions starting on or after the date
     * @throws IllegalArgumentException if date is null
     */
    List<Subscription> findByStartDateAfter(LocalDate date);
    
    /**
     * Finds all subscriptions that end on or before the specified date.
     * 
     * @param date the date to filter by
     * @return a list of subscriptions ending on or before the date
     * @throws IllegalArgumentException if date is null
     */
    List<Subscription> findByEndDateBefore(LocalDate date);
    
    /**
     * Finds all subscriptions created on the specified date.
     * 
     * @param date the date to filter by
     * @return a list of subscriptions created on the date
     * @throws IllegalArgumentException if date is null
     */
    List<Subscription> findByCreatedDate(LocalDate date);
    
    /**
     * Finds all subscriptions.
     * 
     * @return a list of all subscriptions
     */
    List<Subscription> findAll();
    
    /**
     * Checks if a subscription exists with the specified ID.
     * 
     * @param subscriptionId the subscription ID to check
     * @return true if the subscription exists, false otherwise
     * @throws IllegalArgumentException if subscriptionId is null or empty
     */
    boolean existsById(String subscriptionId);
    
    /**
     * Deletes a subscription by its ID.
     * 
     * @param subscriptionId the subscription ID to delete
     * @return true if the subscription was deleted, false if not found
     * @throws IllegalArgumentException if subscriptionId is null or empty
     */
    boolean deleteById(String subscriptionId);
    
    /**
     * Counts the total number of subscriptions.
     * 
     * @return the total number of subscriptions
     */
    long count();
    
    /**
     * Counts the number of subscriptions with the specified status.
     * 
     * @param status the status to count
     * @return the number of subscriptions with the specified status
     * @throws IllegalArgumentException if status is null
     */
    long countByStatus(SubscriptionStatus status);
    
    /**
     * Counts the number of active subscriptions.
     * 
     * @return the number of active subscriptions
     */
    long countActiveSubscriptions();
    
    /**
     * Counts the number of expired subscriptions.
     * 
     * @return the number of expired subscriptions
     */
    long countExpiredSubscriptions();
    
    /**
     * Counts the number of subscriptions expiring on or before the specified date.
     * 
     * @param date the date to count
     * @return the number of subscriptions expiring on or before the date
     * @throws IllegalArgumentException if date is null
     */
    long countExpiringBy(LocalDate date);
    
    /**
     * Calculates the total revenue from all subscriptions.
     * 
     * @return the total revenue
     */
    double calculateTotalRevenue();
    
    /**
     * Calculates the total revenue from active subscriptions.
     * 
     * @return the total revenue from active subscriptions
     */
    double calculateActiveRevenue();
}
