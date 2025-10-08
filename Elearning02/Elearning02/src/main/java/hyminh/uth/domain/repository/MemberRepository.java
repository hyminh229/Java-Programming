package hyminh.uth.domain.repository;

import hyminh.uth.domain.entity.Member;
import hyminh.uth.domain.valueobject.MemberId;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing Member entities.
 * This interface defines the contract for member data access operations.
 * 
 * @author Gym Management System
 * @version 1.0
 */
public interface MemberRepository {
    
    /**
     * Saves a member to the repository.
     * 
     * @param member the member to save
     * @return the saved member
     * @throws IllegalArgumentException if member is null
     */
    Member save(Member member);
    
    /**
     * Finds a member by their ID.
     * 
     * @param memberId the member ID to search for
     * @return an Optional containing the member if found, empty otherwise
     * @throws IllegalArgumentException if memberId is null
     */
    Optional<Member> findById(MemberId memberId);
    
    /**
     * Finds a member by their user ID.
     * 
     * @param userId the user ID to search for
     * @return an Optional containing the member if found, empty otherwise
     * @throws IllegalArgumentException if userId is null or empty
     */
    Optional<Member> findByUserId(String userId);
    
    /**
     * Finds all members registered on or after the specified date.
     * 
     * @param date the date to filter by
     * @return a list of members registered on or after the date
     * @throws IllegalArgumentException if date is null
     */
    List<Member> findByRegistrationDateAfter(LocalDate date);
    
    /**
     * Finds all members registered on or before the specified date.
     * 
     * @param date the date to filter by
     * @return a list of members registered on or before the date
     * @throws IllegalArgumentException if date is null
     */
    List<Member> findByRegistrationDateBefore(LocalDate date);
    
    /**
     * Finds all members with active subscriptions.
     * 
     * @return a list of members with active subscriptions
     */
    List<Member> findWithActiveSubscriptions();
    
    /**
     * Finds all members without active subscriptions.
     * 
     * @return a list of members without active subscriptions
     */
    List<Member> findWithoutActiveSubscriptions();
    
    /**
     * Finds all members assigned to a specific trainer.
     * 
     * @param trainerId the trainer ID to filter by
     * @return a list of members assigned to the trainer
     * @throws IllegalArgumentException if trainerId is null or empty
     */
    List<Member> findByTrainerId(String trainerId);
    
    /**
     * Finds all active members.
     * 
     * @return a list of all active members
     */
    List<Member> findActiveMembers();
    
    /**
     * Finds all inactive members.
     * 
     * @return a list of all inactive members
     */
    List<Member> findInactiveMembers();
    
    /**
     * Finds all members.
     * 
     * @return a list of all members
     */
    List<Member> findAll();
    
    /**
     * Checks if a member exists with the specified ID.
     * 
     * @param memberId the member ID to check
     * @return true if the member exists, false otherwise
     * @throws IllegalArgumentException if memberId is null
     */
    boolean existsById(MemberId memberId);
    
    /**
     * Checks if a member exists with the specified user ID.
     * 
     * @param userId the user ID to check
     * @return true if the member exists, false otherwise
     * @throws IllegalArgumentException if userId is null or empty
     */
    boolean existsByUserId(String userId);
    
    /**
     * Deletes a member by their ID.
     * 
     * @param memberId the member ID to delete
     * @return true if the member was deleted, false if not found
     * @throws IllegalArgumentException if memberId is null
     */
    boolean deleteById(MemberId memberId);
    
    /**
     * Counts the total number of members.
     * 
     * @return the total number of members
     */
    long count();
    
    /**
     * Counts the number of members with active subscriptions.
     * 
     * @return the number of members with active subscriptions
     */
    long countWithActiveSubscriptions();
    
    /**
     * Counts the number of members without active subscriptions.
     * 
     * @return the number of members without active subscriptions
     */
    long countWithoutActiveSubscriptions();
    
    /**
     * Counts the number of members assigned to a specific trainer.
     * 
     * @param trainerId the trainer ID to count
     * @return the number of members assigned to the trainer
     * @throws IllegalArgumentException if trainerId is null or empty
     */
    long countByTrainerId(String trainerId);
    
    /**
     * Counts the number of members registered in a specific month.
     * 
     * @param year the year to count
     * @param month the month to count (1-12)
     * @return the number of members registered in the specified month
     * @throws IllegalArgumentException if year or month is invalid
     */
    long countByRegistrationMonth(int year, int month);
}
