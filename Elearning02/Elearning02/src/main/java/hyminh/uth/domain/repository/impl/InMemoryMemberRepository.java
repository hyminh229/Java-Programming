package hyminh.uth.domain.repository.impl;

import hyminh.uth.domain.entity.Member;
import hyminh.uth.domain.repository.MemberRepository;
import hyminh.uth.domain.valueobject.MemberId;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * In-memory implementation of MemberRepository.
 * This implementation stores members in memory using concurrent data structures.
 * 
 * @author Gym Management System
 * @version 1.0
 */
public class InMemoryMemberRepository implements MemberRepository {
    
    private final Map<MemberId, Member> members = new ConcurrentHashMap<>();
    private final Map<String, MemberId> userIdToMemberId = new ConcurrentHashMap<>();
    private final Map<String, Set<MemberId>> trainerToMembers = new ConcurrentHashMap<>();
    
    @Override
    public Member save(Member member) {
        if (member == null) {
            throw new IllegalArgumentException("Member cannot be null");
        }
        
        MemberId memberId = member.getMemberId();
        String userId = member.getUserId();
        
        // Check for user ID conflicts
        if (userIdToMemberId.containsKey(userId)) {
            MemberId existingMemberId = userIdToMemberId.get(userId);
            if (!existingMemberId.equals(memberId)) {
                throw new IllegalArgumentException("User ID already exists: " + userId);
            }
        }
        
        // Remove old mappings if updating existing member
        Member existingMember = members.get(memberId);
        if (existingMember != null) {
            userIdToMemberId.remove(existingMember.getUserId());
            // Remove from trainer mappings
            removeFromTrainerMappings(existingMember);
        }
        
        // Save member and update mappings
        members.put(memberId, member);
        userIdToMemberId.put(userId, memberId);
        
        // Add to trainer mappings
        addToTrainerMappings(member);
        
        return member;
    }
    
    @Override
    public Optional<Member> findById(MemberId memberId) {
        if (memberId == null) {
            throw new IllegalArgumentException("Member ID cannot be null");
        }
        return Optional.ofNullable(members.get(memberId));
    }
    
    @Override
    public Optional<Member> findByUserId(String userId) {
        if (userId == null || userId.trim().isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }
        MemberId memberId = userIdToMemberId.get(userId);
        return memberId != null ? Optional.ofNullable(members.get(memberId)) : Optional.empty();
    }
    
    @Override
    public List<Member> findByRegistrationDateAfter(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        return members.values().stream()
                .filter(member -> member.getRegistrationDate().isAfter(date))
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Member> findByRegistrationDateBefore(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        return members.values().stream()
                .filter(member -> member.getRegistrationDate().isBefore(date))
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Member> findWithActiveSubscriptions() {
        return members.values().stream()
                .filter(Member::hasActiveSubscription)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Member> findWithoutActiveSubscriptions() {
        return members.values().stream()
                .filter(member -> !member.hasActiveSubscription())
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Member> findByTrainerId(String trainerId) {
        if (trainerId == null || trainerId.trim().isEmpty()) {
            throw new IllegalArgumentException("Trainer ID cannot be null or empty");
        }
        Set<MemberId> memberIds = trainerToMembers.get(trainerId);
        if (memberIds == null) {
            return Collections.emptyList();
        }
        return memberIds.stream()
                .map(members::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Member> findActiveMembers() {
        return members.values().stream()
                .filter(Member::isActive)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Member> findInactiveMembers() {
        return members.values().stream()
                .filter(member -> !member.isActive())
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(members.values());
    }
    
    @Override
    public boolean existsById(MemberId memberId) {
        if (memberId == null) {
            throw new IllegalArgumentException("Member ID cannot be null");
        }
        return members.containsKey(memberId);
    }
    
    @Override
    public boolean existsByUserId(String userId) {
        if (userId == null || userId.trim().isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }
        return userIdToMemberId.containsKey(userId);
    }
    
    @Override
    public boolean deleteById(MemberId memberId) {
        if (memberId == null) {
            throw new IllegalArgumentException("Member ID cannot be null");
        }
        
        Member member = members.remove(memberId);
        if (member != null) {
            userIdToMemberId.remove(member.getUserId());
            removeFromTrainerMappings(member);
            return true;
        }
        return false;
    }
    
    @Override
    public long count() {
        return members.size();
    }
    
    @Override
    public long countWithActiveSubscriptions() {
        return members.values().stream()
                .filter(Member::hasActiveSubscription)
                .count();
    }
    
    @Override
    public long countWithoutActiveSubscriptions() {
        return members.values().stream()
                .filter(member -> !member.hasActiveSubscription())
                .count();
    }
    
    @Override
    public long countByTrainerId(String trainerId) {
        if (trainerId == null || trainerId.trim().isEmpty()) {
            throw new IllegalArgumentException("Trainer ID cannot be null or empty");
        }
        Set<MemberId> memberIds = trainerToMembers.get(trainerId);
        return memberIds != null ? memberIds.size() : 0;
    }
    
    @Override
    public long countByRegistrationMonth(int year, int month) {
        if (year < 1900 || year > 2100) {
            throw new IllegalArgumentException("Year must be between 1900 and 2100");
        }
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Month must be between 1 and 12");
        }
        
        return members.values().stream()
                .filter(member -> {
                    LocalDate regDate = member.getRegistrationDate();
                    return regDate.getYear() == year && regDate.getMonthValue() == month;
                })
                .count();
    }
    
    private void addToTrainerMappings(Member member) {
        // This would need to be implemented based on how trainers are assigned
        // For now, we'll leave this as a placeholder
    }
    
    private void removeFromTrainerMappings(Member member) {
        // This would need to be implemented based on how trainers are assigned
        // For now, we'll leave this as a placeholder
    }
}
