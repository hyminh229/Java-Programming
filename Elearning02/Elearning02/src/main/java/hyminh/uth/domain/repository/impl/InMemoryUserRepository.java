package hyminh.uth.domain.repository.impl;

import hyminh.uth.domain.entity.User;
import hyminh.uth.domain.repository.UserRepository;
import hyminh.uth.domain.valueobject.UserRole;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * In-memory implementation of UserRepository.
 * This implementation stores users in memory using concurrent data structures.
 * 
 * @author Gym Management System
 * @version 1.0
 */
public class InMemoryUserRepository implements UserRepository {
    
    private final Map<String, User> users = new ConcurrentHashMap<>();
    private final Map<String, String> usernameToUserId = new ConcurrentHashMap<>();
    private final Map<String, String> emailToUserId = new ConcurrentHashMap<>();
    
    @Override
    public User save(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        
        String userId = user.getUserId();
        String username = user.getUsername();
        String email = user.getEmail();
        
        // Check for username conflicts
        if (usernameToUserId.containsKey(username)) {
            String existingUserId = usernameToUserId.get(username);
            if (!existingUserId.equals(userId)) {
                throw new IllegalArgumentException("Username already exists: " + username);
            }
        }
        
        // Check for email conflicts
        if (emailToUserId.containsKey(email)) {
            String existingUserId = emailToUserId.get(email);
            if (!existingUserId.equals(userId)) {
                throw new IllegalArgumentException("Email already exists: " + email);
            }
        }
        
        // Remove old mappings if updating existing user
        User existingUser = users.get(userId);
        if (existingUser != null) {
            usernameToUserId.remove(existingUser.getUsername());
            emailToUserId.remove(existingUser.getEmail());
        }
        
        // Save user and update mappings
        users.put(userId, user);
        usernameToUserId.put(username, userId);
        emailToUserId.put(email, userId);
        
        return user;
    }
    
    @Override
    public Optional<User> findById(String userId) {
        if (userId == null || userId.trim().isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }
        return Optional.ofNullable(users.get(userId));
    }
    
    @Override
    public Optional<User> findByUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        String userId = usernameToUserId.get(username);
        return userId != null ? Optional.ofNullable(users.get(userId)) : Optional.empty();
    }
    
    @Override
    public Optional<User> findByEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        String userId = emailToUserId.get(email);
        return userId != null ? Optional.ofNullable(users.get(userId)) : Optional.empty();
    }
    
    @Override
    public List<User> findByRole(UserRole role) {
        if (role == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }
        return users.values().stream()
                .filter(user -> user.getRole() == role)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<User> findActiveUsers() {
        return users.values().stream()
                .filter(User::isActive)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<User> findInactiveUsers() {
        return users.values().stream()
                .filter(user -> !user.isActive())
                .collect(Collectors.toList());
    }
    
    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }
    
    @Override
    public boolean existsById(String userId) {
        if (userId == null || userId.trim().isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }
        return users.containsKey(userId);
    }
    
    @Override
    public boolean existsByUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        return usernameToUserId.containsKey(username);
    }
    
    @Override
    public boolean existsByEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        return emailToUserId.containsKey(email);
    }
    
    @Override
    public boolean deleteById(String userId) {
        if (userId == null || userId.trim().isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }
        
        User user = users.remove(userId);
        if (user != null) {
            usernameToUserId.remove(user.getUsername());
            emailToUserId.remove(user.getEmail());
            return true;
        }
        return false;
    }
    
    @Override
    public long count() {
        return users.size();
    }
    
    @Override
    public long countByRole(UserRole role) {
        if (role == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }
        return users.values().stream()
                .filter(user -> user.getRole() == role)
                .count();
    }
    
    @Override
    public long countActiveUsers() {
        return users.values().stream()
                .filter(User::isActive)
                .count();
    }
    
    @Override
    public long countInactiveUsers() {
        return users.values().stream()
                .filter(user -> !user.isActive())
                .count();
    }
}
