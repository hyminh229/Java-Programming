package hyminh.uth.domain.entity;

import hyminh.uth.domain.valueobject.MemberId;
import hyminh.uth.domain.valueobject.SubscriptionStatus;
import hyminh.uth.domain.valueobject.ProgressMetrics;
import hyminh.uth.domain.valueobject.UserRole;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Lớp thực thể Member đại diện cho một thành viên phòng gym với theo dõi gói tập và tiến độ.
 * Lớp này đóng gói tất cả logic nghiệp vụ và trạng thái cụ thể của thành viên.
 * 
 * Các tính năng chính:
 * - Quản lý thông tin thành viên (ID thành viên, ngày đăng ký)
 * - Quản lý gói tập (subscription) hiện tại
 * - Theo dõi tiến độ tập luyện (cân nặng, tỷ lệ mỡ, số buổi tập)
 * - Quản lý lịch tập và điểm danh
 * - Tính toán các chỉ số như tỷ lệ tham gia, thời gian thành viên
 * 
 * @author Gym Management System
 * @version 1.0
 */
public class Member extends User {
    
    // Các thuộc tính cụ thể của thành viên
    private final MemberId memberId;                    // ID duy nhất của thành viên
    private final LocalDate registrationDate;           // Ngày đăng ký thành viên
    private Subscription currentSubscription;           // Gói tập hiện tại (có thể null)
    private final List<String> workoutScheduleIds;      // Danh sách ID lịch tập
    private final List<String> attendanceIds;           // Danh sách ID điểm danh
    private ProgressMetrics progressMetrics;            // Chỉ số tiến độ tập luyện
    
    /**
     * Khởi tạo một Member mới với các tham số được chỉ định.
     * 
     * @param userId ID duy nhất của người dùng
     * @param username tên đăng nhập
     * @param password mật khẩu để xác thực
     * @param email địa chỉ email
     * @param phone số điện thoại
     * @param memberId ID duy nhất của thành viên
     * @throws IllegalArgumentException nếu bất kỳ tham số nào không hợp lệ
     */
    public Member(String userId, String username, String password, 
                  String email, String phone, MemberId memberId) {
        // Gọi constructor của lớp cha User với vai trò MEMBER
        super(userId, username, password, email, phone, UserRole.MEMBER);
        
        // Validate memberId
        if (memberId == null) {
            throw new IllegalArgumentException("Member ID cannot be null");
        }
        
        // Khởi tạo các thuộc tính cụ thể của thành viên
        this.memberId = memberId;
        this.registrationDate = LocalDate.now();                    // Ngày đăng ký là hôm nay
        this.workoutScheduleIds = new ArrayList<>();                // Khởi tạo danh sách lịch tập rỗng
        this.attendanceIds = new ArrayList<>();                     // Khởi tạo danh sách điểm danh rỗng
        this.progressMetrics = new ProgressMetrics(memberId.getValue(), LocalDate.now()); // Khởi tạo chỉ số tiến độ
    }
    
    /**
     * Gán gói tập cho thành viên này.
     * 
     * @param subscription gói tập cần gán
     * @throws IllegalArgumentException nếu gói tập là null
     */
    public void assignSubscription(Subscription subscription) {
        if (subscription == null) {
            throw new IllegalArgumentException("Subscription cannot be null");
        }
        this.currentSubscription = subscription;  // Gán gói tập mới
    }
    
    /**
     * Removes the current subscription from this member.
     */
    public void removeSubscription() {
        this.currentSubscription = null;
    }
    
    /**
     * Kiểm tra xem thành viên có gói tập đang hoạt động không.
     * 
     * @return true nếu thành viên có gói tập đang hoạt động, false nếu không
     */
    public boolean hasActiveSubscription() {
        // Kiểm tra gói tập có tồn tại và có trạng thái ACTIVE không
        return currentSubscription != null && 
               currentSubscription.getStatus() == SubscriptionStatus.ACTIVE;
    }
    
    /**
     * Adds a workout schedule ID to the member's list.
     * 
     * @param scheduleId the workout schedule ID to add
     * @throws IllegalArgumentException if scheduleId is null or empty
     */
    public void addWorkoutSchedule(String scheduleId) {
        if (scheduleId == null || scheduleId.trim().isEmpty()) {
            throw new IllegalArgumentException("Schedule ID cannot be null or empty");
        }
        if (!workoutScheduleIds.contains(scheduleId)) {
            workoutScheduleIds.add(scheduleId);
        }
    }
    
    /**
     * Removes a workout schedule ID from the member's list.
     * 
     * @param scheduleId the workout schedule ID to remove
     */
    public void removeWorkoutSchedule(String scheduleId) {
        workoutScheduleIds.remove(scheduleId);
    }
    
    /**
     * Adds an attendance ID to the member's list.
     * 
     * @param attendanceId the attendance ID to add
     * @throws IllegalArgumentException if attendanceId is null or empty
     */
    public void addAttendance(String attendanceId) {
        if (attendanceId == null || attendanceId.trim().isEmpty()) {
            throw new IllegalArgumentException("Attendance ID cannot be null or empty");
        }
        if (!attendanceIds.contains(attendanceId)) {
            attendanceIds.add(attendanceId);
        }
    }
    
    /**
     * Updates the member's progress metrics.
     * 
     * @param weight the new weight
     * @param bodyFat the new body fat percentage
     * @param workoutsCompleted the number of workouts completed
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public void updateProgress(double weight, double bodyFat, int workoutsCompleted) {
        if (weight < 0) {
            throw new IllegalArgumentException("Weight cannot be negative");
        }
        if (bodyFat < 0 || bodyFat > 100) {
            throw new IllegalArgumentException("Body fat percentage must be between 0 and 100");
        }
        if (workoutsCompleted < 0) {
            throw new IllegalArgumentException("Workouts completed cannot be negative");
        }
        
        this.progressMetrics = new ProgressMetrics(
            memberId.getValue(), 
            LocalDate.now(), 
            weight, 
            bodyFat, 
            workoutsCompleted,
            ""
        );
    }
    
    /**
     * Increments the number of completed workouts.
     */
    public void incrementWorkouts() {
        this.progressMetrics = this.progressMetrics.incrementWorkouts();
    }
    
    /**
     * Calculates the member's membership duration in days.
     * 
     * @return the number of days since registration
     */
    public long getMembershipDurationInDays() {
        return java.time.temporal.ChronoUnit.DAYS.between(registrationDate, LocalDate.now());
    }
    
    /**
     * Calculates the member's attendance rate.
     * 
     * @return the attendance rate as a percentage (0.0 to 100.0)
     */
    public double getAttendanceRate() {
        if (workoutScheduleIds.isEmpty()) {
            return 0.0;
        }
        return (double) attendanceIds.size() / workoutScheduleIds.size() * 100.0;
    }
    
    // Getters
    public MemberId getMemberId() { return memberId; }
    public LocalDate getRegistrationDate() { return registrationDate; }
    public Subscription getCurrentSubscription() { return currentSubscription; }
    public List<String> getWorkoutScheduleIds() { return Collections.unmodifiableList(workoutScheduleIds); }
    public List<String> getAttendanceIds() { return Collections.unmodifiableList(attendanceIds); }
    public ProgressMetrics getProgressMetrics() { return progressMetrics; }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;
        Member member = (Member) obj;
        return Objects.equals(memberId, member.memberId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), memberId);
    }
    
    @Override
    public String toString() {
        return String.format("Member{memberId=%s, username='%s', email='%s', subscription=%s, workouts=%d, attendance=%d}", 
                           memberId, getUsername(), getEmail(), 
                           currentSubscription != null ? currentSubscription.getStatus() : "None",
                           workoutScheduleIds.size(), attendanceIds.size());
    }
}
