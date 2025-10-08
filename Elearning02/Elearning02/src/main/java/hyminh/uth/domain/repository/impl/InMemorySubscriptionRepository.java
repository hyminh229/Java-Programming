package hyminh.uth.domain.repository.impl;

import hyminh.uth.domain.entity.Subscription;
import hyminh.uth.domain.repository.SubscriptionRepository;
import hyminh.uth.domain.valueobject.SubscriptionStatus;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Triển khai trong bộ nhớ của SubscriptionRepository.
 * Triển khai này lưu trữ subscriptions trong bộ nhớ sử dụng cấu trúc dữ liệu đồng thời.
 * 
 * @author Gym Management System
 * @version 1.0
 */
public class InMemorySubscriptionRepository implements SubscriptionRepository {
    
    private final Map<String, Subscription> subscriptions = new ConcurrentHashMap<>();
    
    @Override
    public Subscription save(Subscription subscription) {
        if (subscription == null) {
            throw new IllegalArgumentException("Subscription cannot be null");
        }
        subscriptions.put(subscription.getSubscriptionId(), subscription);
        return subscription;
    }
    
    @Override
    public Optional<Subscription> findById(String subscriptionId) {
        if (subscriptionId == null || subscriptionId.trim().isEmpty()) {
            throw new IllegalArgumentException("Subscription ID cannot be null or empty");
        }
        return Optional.ofNullable(subscriptions.get(subscriptionId));
    }
    
    @Override
    public List<Subscription> findByStatus(SubscriptionStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        return subscriptions.values().stream()
                .filter(subscription -> subscription.getStatus() == status)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Subscription> findActiveSubscriptions() {
        return subscriptions.values().stream()
                .filter(Subscription::isActive)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Subscription> findExpiredSubscriptions() {
        return subscriptions.values().stream()
                .filter(Subscription::isExpired)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Subscription> findExpiringBy(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        return subscriptions.values().stream()
                .filter(subscription -> !subscription.isExpired() && 
                        subscription.getEndDate().isBefore(date) || 
                        subscription.getEndDate().isEqual(date))
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Subscription> findByStartDateAfter(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        return subscriptions.values().stream()
                .filter(subscription -> subscription.getStartDate().isAfter(date))
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Subscription> findByEndDateBefore(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        return subscriptions.values().stream()
                .filter(subscription -> subscription.getEndDate().isBefore(date))
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Subscription> findByCreatedDate(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        return subscriptions.values().stream()
                .filter(subscription -> subscription.getCreatedAt().equals(date))
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Subscription> findAll() {
        return new ArrayList<>(subscriptions.values());
    }
    
    @Override
    public boolean existsById(String subscriptionId) {
        if (subscriptionId == null || subscriptionId.trim().isEmpty()) {
            throw new IllegalArgumentException("Subscription ID cannot be null or empty");
        }
        return subscriptions.containsKey(subscriptionId);
    }
    
    @Override
    public boolean deleteById(String subscriptionId) {
        if (subscriptionId == null || subscriptionId.trim().isEmpty()) {
            throw new IllegalArgumentException("Subscription ID cannot be null or empty");
        }
        return subscriptions.remove(subscriptionId) != null;
    }
    
    @Override
    public long count() {
        return subscriptions.size();
    }
    
    @Override
    public long countByStatus(SubscriptionStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        return subscriptions.values().stream()
                .filter(subscription -> subscription.getStatus() == status)
                .count();
    }
    
    @Override
    public long countActiveSubscriptions() {
        return subscriptions.values().stream()
                .filter(Subscription::isActive)
                .count();
    }
    
    @Override
    public long countExpiredSubscriptions() {
        return subscriptions.values().stream()
                .filter(Subscription::isExpired)
                .count();
    }
    
    @Override
    public long countExpiringBy(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        return subscriptions.values().stream()
                .filter(subscription -> !subscription.isExpired() && 
                        subscription.getEndDate().isBefore(date) || 
                        subscription.getEndDate().isEqual(date))
                .count();
    }
    
    @Override
    public double calculateTotalRevenue() {
        return subscriptions.values().stream()
                .mapToDouble(Subscription::getAmount)
                .sum();
    }
    
    @Override
    public double calculateActiveRevenue() {
        return subscriptions.values().stream()
                .filter(Subscription::isActive)
                .mapToDouble(Subscription::getAmount)
                .sum();
    }
}
