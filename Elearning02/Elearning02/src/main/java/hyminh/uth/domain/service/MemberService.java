package hyminh.uth.domain.service;

import hyminh.uth.domain.entity.Member;
import hyminh.uth.domain.entity.Subscription;
import hyminh.uth.domain.repository.MemberRepository;
import hyminh.uth.domain.repository.SubscriptionRepository;
import hyminh.uth.domain.valueobject.MemberId;
import hyminh.uth.domain.exception.MemberNotFoundException;
import hyminh.uth.domain.exception.SubscriptionNotFoundException;
import hyminh.uth.domain.exception.InvalidSubscriptionException;
import java.time.LocalDate;
import java.util.List;

/**
 * Service class for managing Member-related business operations.
 * This class encapsulates the business logic for member management.
 * 
 * @author Gym Management System
 * @version 1.0
 */
public class MemberService {
    
    private final MemberRepository memberRepository;
    private final SubscriptionRepository subscriptionRepository;
    
    /**
     * Constructs a new MemberService with the specified repositories.
     * 
     * @param memberRepository the member repository
     * @param subscriptionRepository the subscription repository
     * @throws IllegalArgumentException if any parameter is null
     */
    public MemberService(MemberRepository memberRepository, SubscriptionRepository subscriptionRepository) {
        if (memberRepository == null) {
            throw new IllegalArgumentException("Member repository cannot be null");
        }
        if (subscriptionRepository == null) {
            throw new IllegalArgumentException("Subscription repository cannot be null");
        }
        this.memberRepository = memberRepository;
        this.subscriptionRepository = subscriptionRepository;
    }
    
    /**
     * Creates a new member.
     * 
     * @param userId the user ID
     * @param username the username
     * @param password the password
     * @param email the email
     * @param phone the phone
     * @param memberId the member ID
     * @return the created member
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public Member createMember(String userId, String username, String password, 
                              String email, String phone, MemberId memberId) {
        if (memberRepository.existsByUserId(userId)) {
            throw new IllegalArgumentException("User ID already exists: " + userId);
        }
        
        Member member = new Member(userId, username, password, email, phone, memberId);
        return memberRepository.save(member);
    }
    
    /**
     * Finds a member by their ID.
     * 
     * @param memberId the member ID
     * @return the member
     * @throws MemberNotFoundException if the member is not found
     */
    public Member findById(MemberId memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException("Member not found: " + memberId));
    }
    
    /**
     * Finds a member by their user ID.
     * 
     * @param userId the user ID
     * @return the member
     * @throws MemberNotFoundException if the member is not found
     */
    public Member findByUserId(String userId) {
        return memberRepository.findByUserId(userId)
                .orElseThrow(() -> new MemberNotFoundException("Member not found for user ID: " + userId));
    }
    
    /**
     * Assigns a subscription to a member.
     * 
     * @param memberId the member ID
     * @param subscriptionId the subscription ID
     * @throws MemberNotFoundException if the member is not found
     * @throws SubscriptionNotFoundException if the subscription is not found
     * @throws InvalidSubscriptionException if the subscription is invalid
     */
    public void assignSubscription(MemberId memberId, String subscriptionId) {
        Member member = findById(memberId);
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new SubscriptionNotFoundException("Subscription not found: " + subscriptionId));
        
        if (subscription.getStatus().isExpired()) {
            throw new InvalidSubscriptionException("Cannot assign expired subscription");
        }
        
        member.assignSubscription(subscription);
        memberRepository.save(member);
    }
    
    /**
     * Removes a subscription from a member.
     * 
     * @param memberId the member ID
     * @throws MemberNotFoundException if the member is not found
     */
    public void removeSubscription(MemberId memberId) {
        Member member = findById(memberId);
        member.removeSubscription();
        memberRepository.save(member);
    }
    
    /**
     * Updates a member's progress metrics.
     * 
     * @param memberId the member ID
     * @param weight the new weight
     * @param bodyFat the new body fat percentage
     * @param workoutsCompleted the number of workouts completed
     * @throws MemberNotFoundException if the member is not found
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public void updateProgress(MemberId memberId, double weight, double bodyFat, int workoutsCompleted) {
        Member member = findById(memberId);
        member.updateProgress(weight, bodyFat, workoutsCompleted);
        memberRepository.save(member);
    }
    
    /**
     * Increments the number of completed workouts for a member.
     * 
     * @param memberId the member ID
     * @throws MemberNotFoundException if the member is not found
     */
    public void incrementWorkouts(MemberId memberId) {
        Member member = findById(memberId);
        member.incrementWorkouts();
        memberRepository.save(member);
    }
    
    /**
     * Gets all members with active subscriptions.
     * 
     * @return a list of members with active subscriptions
     */
    public List<Member> getMembersWithActiveSubscriptions() {
        return memberRepository.findWithActiveSubscriptions();
    }
    
    /**
     * Gets all members without active subscriptions.
     * 
     * @return a list of members without active subscriptions
     */
    public List<Member> getMembersWithoutActiveSubscriptions() {
        return memberRepository.findWithoutActiveSubscriptions();
    }
    
    /**
     * Gets all members registered in a specific month.
     * 
     * @param year the year
     * @param month the month (1-12)
     * @return a list of members registered in the specified month
     * @throws IllegalArgumentException if year or month is invalid
     */
    public List<Member> getMembersByRegistrationMonth(int year, int month) {
        if (year < 1900 || year > 2100) {
            throw new IllegalArgumentException("Year must be between 1900 and 2100");
        }
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Month must be between 1 and 12");
        }
        
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
        
        return memberRepository.findAll().stream()
                .filter(member -> {
                    LocalDate regDate = member.getRegistrationDate();
                    return !regDate.isBefore(startDate) && !regDate.isAfter(endDate);
                })
                .collect(java.util.stream.Collectors.toList());
    }
    
    /**
     * Calculates the total number of members.
     * 
     * @return the total number of members
     */
    public long getTotalMemberCount() {
        return memberRepository.count();
    }
    
    /**
     * Calculates the number of members with active subscriptions.
     * 
     * @return the number of members with active subscriptions
     */
    public long getActiveSubscriptionCount() {
        return memberRepository.countWithActiveSubscriptions();
    }
    
    /**
     * Calculates the member retention rate.
     * 
     * @return the member retention rate as a percentage
     */
    public double getMemberRetentionRate() {
        long totalMembers = memberRepository.count();
        long activeSubscriptions = memberRepository.countWithActiveSubscriptions();
        
        if (totalMembers == 0) {
            return 0.0;
        }
        
        return (double) activeSubscriptions / totalMembers * 100.0;
    }
}
