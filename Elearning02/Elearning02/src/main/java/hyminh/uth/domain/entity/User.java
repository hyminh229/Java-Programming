package hyminh.uth.domain.entity;

import hyminh.uth.domain.valueobject.UserRole;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Lớp thực thể User cơ bản đại diện cho một người trong hệ thống quản lý phòng gym.
 * Đây là lớp trừu tượng định nghĩa các thuộc tính và hành vi chung của người dùng.
 * 
 * Các tính năng chính:
 * - Quản lý thông tin cá nhân (ID, tên đăng nhập, email, số điện thoại)
 * - Xác thực người dùng với tên đăng nhập và mật khẩu
 * - Cập nhật thông tin cá nhân với validation
 * - Quản lý trạng thái hoạt động của tài khoản
 * - Theo dõi thời gian tạo và cập nhật cuối cùng
 * 
 * @author Gym Management System
 * @version 1.0
 */
public abstract class User {
    
    // Mẫu regex để validate định dạng email hợp lệ
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
    );
    // Mẫu regex để validate định dạng số điện thoại hợp lệ (có thể có dấu + ở đầu)
    private static final Pattern PHONE_PATTERN = Pattern.compile(
        "^\\+?[1-9]\\d{1,14}$"
    );
    
    // Các thuộc tính cơ bản của người dùng
    private final String userId;           // ID duy nhất của người dùng (không thể thay đổi)
    private final String username;         // Tên đăng nhập (không thể thay đổi)
    private String password;               // Mật khẩu (có thể thay đổi)
    private String email;                  // Email (có thể thay đổi)
    private String phone;                  // Số điện thoại (có thể thay đổi)
    private final UserRole role;           // Vai trò của người dùng (không thể thay đổi)
    private final LocalDateTime createdAt; // Thời gian tạo tài khoản (không thể thay đổi)
    private LocalDateTime lastModifiedAt;  // Thời gian cập nhật cuối cùng
    private boolean isActive;              // Trạng thái hoạt động của tài khoản
    
    /**
     * Khởi tạo một User mới với các tham số được chỉ định.
     * 
     * @param userId ID duy nhất của người dùng
     * @param username tên đăng nhập
     * @param password mật khẩu để xác thực
     * @param email địa chỉ email
     * @param phone số điện thoại
     * @param role vai trò của người dùng
     * @throws IllegalArgumentException nếu bất kỳ tham số nào không hợp lệ
     */
    protected User(String userId, String username, String password, 
                   String email, String phone, UserRole role) {
        // Validate tất cả các tham số đầu vào trước khi khởi tạo
        validateUserId(userId);
        validateUsername(username);
        validatePassword(password);
        validateEmail(email);
        validatePhone(phone);
        validateRole(role);
        
        // Gán giá trị cho các thuộc tính
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.createdAt = LocalDateTime.now();        // Thời gian tạo là hiện tại
        this.lastModifiedAt = LocalDateTime.now();   // Thời gian cập nhật cuối cùng là hiện tại
        this.isActive = true;                        // Mặc định tài khoản được kích hoạt
    }
    
    /**
     * Xác thực người dùng với thông tin đăng nhập được cung cấp.
     * 
     * @param username tên đăng nhập để xác thực
     * @param password mật khẩu để xác thực
     * @return true nếu xác thực thành công, false nếu không
     */
    public boolean authenticate(String username, String password) {
        // Kiểm tra tài khoản có đang hoạt động không
        if (!isActive) {
            return false;
        }
        // So sánh tên đăng nhập và mật khẩu
        return this.username.equals(username) && this.password.equals(password);
    }
    
    /**
     * Cập nhật mật khẩu của người dùng.
     * 
     * @param newPassword mật khẩu mới
     * @throws IllegalArgumentException nếu mật khẩu mới không hợp lệ
     */
    public void updatePassword(String newPassword) {
        validatePassword(newPassword);              // Validate mật khẩu mới
        this.password = newPassword;                // Cập nhật mật khẩu
        this.lastModifiedAt = LocalDateTime.now();  // Cập nhật thời gian sửa đổi cuối cùng
    }
    
    /**
     * Updates the user's email address.
     * 
     * @param newEmail the new email address
     * @throws IllegalArgumentException if the new email is invalid
     */
    public void updateEmail(String newEmail) {
        validateEmail(newEmail);
        this.email = newEmail;
        this.lastModifiedAt = LocalDateTime.now();
    }
    
    /**
     * Updates the user's phone number.
     * 
     * @param newPhone the new phone number
     * @throws IllegalArgumentException if the new phone number is invalid
     */
    public void updatePhone(String newPhone) {
        validatePhone(newPhone);
        this.phone = newPhone;
        this.lastModifiedAt = LocalDateTime.now();
    }
    
    /**
     * Deactivates the user account.
     */
    public void deactivate() {
        this.isActive = false;
        this.lastModifiedAt = LocalDateTime.now();
    }
    
    /**
     * Activates the user account.
     */
    public void activate() {
        this.isActive = true;
        this.lastModifiedAt = LocalDateTime.now();
    }
    
    // ========== CÁC PHƯƠNG THỨC VALIDATION ==========
    
    /**
     * Kiểm tra tính hợp lệ của User ID
     * @param userId ID người dùng cần kiểm tra
     * @throws IllegalArgumentException nếu ID không hợp lệ
     */
    private void validateUserId(String userId) {
        if (userId == null || userId.trim().isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }
    }
    
    /**
     * Kiểm tra tính hợp lệ của tên đăng nhập
     * @param username tên đăng nhập cần kiểm tra
     * @throws IllegalArgumentException nếu tên đăng nhập không hợp lệ
     */
    private void validateUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if (username.length() < 3 || username.length() > 50) {
            throw new IllegalArgumentException("Username must be between 3 and 50 characters");
        }
    }
    
    /**
     * Kiểm tra tính hợp lệ của mật khẩu
     * @param password mật khẩu cần kiểm tra
     * @throws IllegalArgumentException nếu mật khẩu không hợp lệ
     */
    private void validatePassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        if (password.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters long");
        }
    }
    
    /**
     * Kiểm tra tính hợp lệ của email
     * @param email email cần kiểm tra
     * @throws IllegalArgumentException nếu email không hợp lệ
     */
    private void validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }
    
    /**
     * Kiểm tra tính hợp lệ của số điện thoại
     * @param phone số điện thoại cần kiểm tra
     * @throws IllegalArgumentException nếu số điện thoại không hợp lệ
     */
    private void validatePhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone cannot be null or empty");
        }
        if (!PHONE_PATTERN.matcher(phone).matches()) {
            throw new IllegalArgumentException("Invalid phone number format");
        }
    }
    
    /**
     * Kiểm tra tính hợp lệ của vai trò người dùng
     * @param role vai trò cần kiểm tra
     * @throws IllegalArgumentException nếu vai trò không hợp lệ
     */
    private void validateRole(UserRole role) {
        if (role == null) {
            throw new IllegalArgumentException("User role cannot be null");
        }
    }
    
    // Getters
    public String getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public UserRole getRole() { return role; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getLastModifiedAt() { return lastModifiedAt; }
    public boolean isActive() { return isActive; }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return Objects.equals(userId, user.userId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
    
    @Override
    public String toString() {
        return String.format("User{userId='%s', username='%s', email='%s', role=%s, isActive=%s}", 
                           userId, username, email, role, isActive);
    }
}
