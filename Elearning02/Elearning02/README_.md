# Hệ Thống Quản Lý Phòng Gym - Tầng Domain

Dự án này triển khai một tầng domain (domain layer) toàn diện cho hệ thống quản lý phòng gym tuân theo các nguyên tắc SOLID, thực hành clean code và mô hình domain phong phú.

## Tổng Quan Kiến Trúc

Tầng domain được tổ chức thành các package sau:

```
src/main/java/hyminh/uth/domain/
├── entity/           # Các thực thể domain với logic nghiệp vụ
├── valueobject/      # Các đối tượng giá trị bất biến
├── repository/       # Các interface repository
├── repository/impl/  # Triển khai repository trong bộ nhớ
├── service/          # Các dịch vụ domain
├── dto/              # Các đối tượng chuyển dữ liệu
└── exception/        # Các ngoại lệ cụ thể của domain
```

## Các Thực Thể Domain

### Thực Thể Cốt Lõi

1. **User** (Lớp Cơ Sở Trừu Tượng)
   - Lớp cơ sở cho tất cả các loại người dùng
   - Xử lý xác thực và các thuộc tính chung của người dùng
   - Triển khai validation và các quy tắc nghiệp vụ

2. **Member** (Kế Thừa User)
   - Đại diện cho các thành viên phòng gym
   - Quản lý gói tập và theo dõi tiến độ
   - Xử lý lịch tập và điểm danh

3. **Trainer** (Kế Thừa User)
   - Đại diện cho các huấn luyện viên
   - Quản lý phân công thành viên và chuyên môn
   - Theo dõi kinh nghiệm và tình trạng sẵn sàng

4. **Admin** (Kế Thừa User)
   - Đại diện cho quản trị viên hệ thống
   - Quản lý quyền hạn và truy cập hệ thống
   - Xử lý các thao tác quản trị

5. **Subscription**
   - Đại diện cho gói tập của thành viên
   - Quản lý vòng đời và trạng thái gói tập
   - Xử lý logic gia hạn và hủy bỏ

6. **Exercise**
   - Đại diện cho các bài tập
   - Quản lý thuộc tính và độ khó của bài tập
   - Xử lý thiết bị và nhóm cơ mục tiêu

## Các Đối Tượng Giá Trị (Value Objects)

### Đối Tượng Giá Trị Liên Quan Đến Người Dùng

- **UserRole**: Enum đại diện cho vai trò người dùng (ADMIN, TRAINER, MEMBER)
- **MemberId**: Định danh duy nhất cho thành viên
- **AdminAction**: Enum đại diện cho các hành động quản trị
- **Specialization**: Enum đại diện cho chuyên môn của huấn luyện viên

### Đối Tượng Giá Trị Liên Quan Đến Gói Tập

- **SubscriptionStatus**: Enum đại diện cho trạng thái gói tập
- **SubscriptionPlan**: Đại diện cho các gói tập với tính năng
- **PlanType**: Enum đại diện cho loại gói (BASIC, STANDARD, PREMIUM, VIP)

### Đối Tượng Giá Trị Liên Quan Đến Bài Tập

- **ExerciseType**: Enum đại diện cho loại bài tập
- **DifficultyLevel**: Enum đại diện cho mức độ khó
- **ProgressMetrics**: Đại diện cho dữ liệu tiến độ thành viên

## Các Interface Repository

### UserRepository
- Quản lý các thao tác truy cập dữ liệu người dùng
- Hỗ trợ tìm kiếm theo ID, tên đăng nhập, email và vai trò
- Xử lý kích hoạt/vô hiệu hóa người dùng

### MemberRepository
- Quản lý truy cập dữ liệu cụ thể của thành viên
- Hỗ trợ tìm kiếm theo ngày đăng ký, trạng thái gói tập
- Xử lý phân công huấn luyện viên và theo dõi tiến độ

### SubscriptionRepository
- Quản lý truy cập dữ liệu gói tập
- Hỗ trợ tìm kiếm theo trạng thái, khoảng thời gian
- Xử lý tính toán doanh thu

### ExerciseRepository
- Quản lý truy cập dữ liệu bài tập
- Hỗ trợ tìm kiếm theo loại, độ khó, thiết bị
- Xử lý tìm kiếm và lọc

## Các Dịch Vụ Domain

### MemberService
- Đóng gói logic nghiệp vụ liên quan đến thành viên
- Xử lý tạo thành viên, cập nhật tiến độ
- Quản lý phân công gói tập
- Tính toán tỷ lệ duy trì và thống kê

## Các Đối Tượng Chuyển Dữ Liệu (DTOs)

### MemberDTO
- Đại diện cho dữ liệu thành viên để chuyển giữa các tầng
- Bao gồm thông tin gói tập và tiến độ
- Cung cấp các thuộc tính được tính toán như tỷ lệ tham gia

### ExerciseDTO
- Đại diện cho dữ liệu bài tập để chuyển
- Bao gồm thuộc tính bài tập và tính toán
- Hỗ trợ kiểm tra phù hợp cho các cấp độ người dùng khác nhau

## Các Ngoại Lệ

### Ngoại Lệ Domain
- **MemberNotFoundException**: Ném khi không tìm thấy thành viên
- **SubscriptionNotFoundException**: Ném khi không tìm thấy gói tập
- **InvalidSubscriptionException**: Ném khi thao tác gói tập không hợp lệ

## Các Tính Năng Chính

### Mô Hình Domain Phong Phú
- Các thực thể chứa logic nghiệp vụ và validation
- Các đối tượng giá trị đảm bảo an toàn kiểu và bất biến
- Các dịch vụ domain đóng gói các thao tác nghiệp vụ phức tạp

### Nguyên Tắc SOLID
- **Single Responsibility**: Mỗi lớp có một lý do để thay đổi
- **Open/Closed**: Mở để mở rộng, đóng để sửa đổi
- **Liskov Substitution**: Các kiểu con có thể thay thế cho kiểu cơ sở
- **Interface Segregation**: Client chỉ phụ thuộc vào các interface chúng sử dụng
- **Dependency Inversion**: Phụ thuộc vào trừu tượng, không phải cụ thể

### Thực Hành Clean Code
- Tài liệu Javadoc toàn diện
- Tên phương thức và biến có ý nghĩa
- Xử lý lỗi và validation phù hợp
- Các đối tượng giá trị bất biến khi thích hợp

### Kiểm Thử Toàn Diện
- Unit test cho các thực thể và dịch vụ
- Kiểm thử dựa trên mock cho tầng dịch vụ
- Phủ sóng kiểm thử cho các trường hợp biên và điều kiện lỗi

## Ví Dụ Sử Dụng

### Tạo Một Thành Viên
```java
MemberId memberId = new MemberId("MEM-000001");
Member member = new Member("USER-001", "john_doe", "password123", 
                          "john@example.com", "+1234567890", memberId);
```

### Tạo Một Gói Tập
```java
SubscriptionPlan plan = SubscriptionPlan.createPremium("PLAN-001", "Premium Plan", 
                                                      12, 99.99);
```

### Tạo Một Bài Tập
```java
Exercise exercise = Exercise.createSimple("EX-001", "Push-ups", 
                                         ExerciseType.STRENGTH, DifficultyLevel.BEGINNER,
                                         "Upper body strength exercise", 
                                         "Chest, Shoulders, Triceps", "None");
```

### Sử Dụng Dịch Vụ Domain
```java
MemberService memberService = new MemberService(memberRepository, subscriptionRepository);
Member member = memberService.createMember("USER-001", "john_doe", "password123", 
                                          "john@example.com", "+1234567890", memberId);
memberService.updateProgress(memberId, 75.5, 15.0, 10);
```

## Giải Thích Chi Tiết Các Thành Phần

### 1. Lớp User (Lớp Cơ Sở)

```java
public abstract class User {
    // Các thuộc tính cơ bản của người dùng
    private final String userId;           // ID duy nhất (không thể thay đổi)
    private final String username;         // Tên đăng nhập (không thể thay đổi)
    private String password;               // Mật khẩu (có thể thay đổi)
    private String email;                  // Email (có thể thay đổi)
    private String phone;                  // Số điện thoại (có thể thay đổi)
    private final UserRole role;           // Vai trò (không thể thay đổi)
    private final LocalDateTime createdAt; // Thời gian tạo (không thể thay đổi)
    private LocalDateTime lastModifiedAt;  // Thời gian cập nhật cuối cùng
    private boolean isActive;              // Trạng thái hoạt động
}
```

**Tính năng chính:**
- **Validation mạnh mẽ**: Tất cả dữ liệu đầu vào đều được validate
- **Bảo mật**: Mật khẩu được bảo vệ, chỉ có thể thay đổi thông qua phương thức chuyên dụng
- **Theo dõi thay đổi**: Tự động cập nhật thời gian sửa đổi cuối cùng
- **Trạng thái tài khoản**: Có thể kích hoạt/vô hiệu hóa tài khoản

### 2. Lớp Member (Thành Viên)

```java
public class Member extends User {
    // Các thuộc tính cụ thể của thành viên
    private final MemberId memberId;                    // ID duy nhất của thành viên
    private final LocalDate registrationDate;           // Ngày đăng ký thành viên
    private Subscription currentSubscription;           // Gói tập hiện tại (có thể null)
    private final List<String> workoutScheduleIds;      // Danh sách ID lịch tập
    private final List<String> attendanceIds;           // Danh sách ID điểm danh
    private ProgressMetrics progressMetrics;            // Chỉ số tiến độ tập luyện
}
```

**Tính năng chính:**
- **Quản lý gói tập**: Có thể gán, hủy gói tập
- **Theo dõi tiến độ**: Cập nhật cân nặng, tỷ lệ mỡ, số buổi tập
- **Quản lý lịch tập**: Thêm/xóa lịch tập
- **Theo dõi điểm danh**: Thêm/xóa điểm danh
- **Tính toán chỉ số**: Tỷ lệ tham gia, thời gian thành viên

### 3. Các Đối Tượng Giá Trị (Value Objects)

#### UserRole
```java
public enum UserRole {
    ADMIN("Administrator", "Full system access and management capabilities"),
    TRAINER("Trainer", "Member management and workout scheduling capabilities"),
    MEMBER("Member", "Basic gym access and personal progress tracking");
}
```

**Đặc điểm:**
- **Bất biến**: Không thể thay đổi sau khi tạo
- **An toàn kiểu**: Đảm bảo chỉ có các vai trò hợp lệ
- **Phương thức tiện ích**: Cung cấp các phương thức kiểm tra vai trò

#### MemberId
```java
public final class MemberId {
    private static final Pattern MEMBER_ID_PATTERN = Pattern.compile("^MEM-\\d{6}$");
    private final String value;
    
    public MemberId(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Member ID cannot be null or empty");
        }
        if (!MEMBER_ID_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("Member ID must follow format MEM-XXXXXX");
        }
        this.value = value;
    }
}
```

**Đặc điểm:**
- **Validation nghiêm ngặt**: Chỉ chấp nhận định dạng MEM-XXXXXX
- **Bất biến**: Không thể thay đổi sau khi tạo
- **So sánh an toàn**: Override equals() và hashCode()

### 4. Repository Pattern

#### UserRepository Interface
```java
public interface UserRepository {
    User save(User user);
    Optional<User> findById(String userId);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    List<User> findByRole(UserRole role);
    List<User> findActiveUsers();
    boolean existsById(String userId);
    boolean deleteById(String userId);
    long count();
}
```

**Lợi ích:**
- **Tách biệt**: Tách logic nghiệp vụ khỏi truy cập dữ liệu
- **Linh hoạt**: Có thể thay đổi triển khai mà không ảnh hưởng đến domain
- **Kiểm thử**: Dễ dàng mock cho unit test

#### InMemoryUserRepository Implementation
```java
public class InMemoryUserRepository implements UserRepository {
    private final Map<String, User> users = new ConcurrentHashMap<>();
    private final Map<String, String> usernameToUserId = new ConcurrentHashMap<>();
    private final Map<String, String> emailToUserId = new ConcurrentHashMap<>();
    
    @Override
    public User save(User user) {
        // Kiểm tra xung đột username và email
        // Cập nhật mappings
        // Lưu user
    }
}
```

**Đặc điểm:**
- **Thread-safe**: Sử dụng ConcurrentHashMap
- **Indexing**: Tạo index cho username và email để tìm kiếm nhanh
- **Validation**: Kiểm tra xung đột dữ liệu

### 5. Domain Services

#### MemberService
```java
public class MemberService {
    private final MemberRepository memberRepository;
    private final SubscriptionRepository subscriptionRepository;
    
    public Member createMember(String userId, String username, String password, 
                              String email, String phone, MemberId memberId) {
        // Kiểm tra user ID đã tồn tại chưa
        // Tạo member mới
        // Lưu vào repository
    }
    
    public void updateProgress(MemberId memberId, double weight, double bodyFat, int workoutsCompleted) {
        // Tìm member
        // Cập nhật tiến độ
        // Lưu thay đổi
    }
}
```

**Tính năng:**
- **Encapsulation**: Đóng gói logic nghiệp vụ phức tạp
- **Transaction**: Đảm bảo tính nhất quán dữ liệu
- **Validation**: Kiểm tra quy tắc nghiệp vụ

### 6. Exception Handling

#### Domain Exceptions
```java
public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException(String message) {
        super(message);
    }
}

public class InvalidSubscriptionException extends RuntimeException {
    public InvalidSubscriptionException(String message) {
        super(message);
    }
}
```

**Lợi ích:**
- **Rõ ràng**: Mỗi loại lỗi có exception riêng
- **Thông tin**: Cung cấp thông tin chi tiết về lỗi
- **Xử lý**: Dễ dàng xử lý từng loại lỗi khác nhau

## Các Design Pattern Được Sử Dụng

### 1. Repository Pattern
- **Mục đích**: Tách biệt logic nghiệp vụ khỏi truy cập dữ liệu
- **Triển khai**: Interface + Implementation
- **Lợi ích**: Dễ test, dễ thay đổi triển khai

### 2. Value Object Pattern
- **Mục đích**: Đại diện cho các giá trị không có danh tính
- **Triển khai**: Immutable objects
- **Lợi ích**: An toàn, dễ sử dụng

### 3. Service Layer Pattern
- **Mục đích**: Đóng gói logic nghiệp vụ phức tạp
- **Triển khai**: Service classes
- **Lợi ích**: Tái sử dụng, dễ test

### 4. DTO Pattern
- **Mục đích**: Chuyển dữ liệu giữa các tầng
- **Triển khai**: Data Transfer Objects
- **Lợi ích**: Tách biệt, hiệu suất

### 5. Factory Pattern
- **Mục đích**: Tạo các đối tượng phức tạp
- **Triển khai**: Static factory methods
- **Lợi ích**: Đơn giản hóa việc tạo đối tượng

## Hướng Dẫn Sử Dụng

### 1. Tạo Thành Viên Mới
```java
// Tạo MemberId
MemberId memberId = new MemberId("MEM-000001");

// Tạo Member
Member member = new Member("USER-001", "john_doe", "password123", 
                          "john@example.com", "+1234567890", memberId);

// Lưu vào repository
memberRepository.save(member);
```

### 2. Cập Nhật Tiến Độ
```java
// Tìm member
Member member = memberRepository.findById(memberId).orElseThrow();

// Cập nhật tiến độ
member.updateProgress(75.5, 15.0, 10);

// Lưu thay đổi
memberRepository.save(member);
```

### 3. Quản Lý Gói Tập
```java
// Tạo gói tập
SubscriptionPlan plan = SubscriptionPlan.createPremium("PLAN-001", "Premium Plan", 12, 99.99);
Subscription subscription = new Subscription("SUB-001", plan, LocalDate.now());

// Gán cho member
member.assignSubscription(subscription);
memberRepository.save(member);
```

### 4. Sử Dụng Domain Service
```java
// Khởi tạo service
MemberService memberService = new MemberService(memberRepository, subscriptionRepository);

// Tạo member
Member member = memberService.createMember("USER-001", "john_doe", "password123", 
                                          "john@example.com", "+1234567890", memberId);

// Cập nhật tiến độ
memberService.updateProgress(memberId, 75.5, 15.0, 10);
```

## Lợi Ích Của Kiến Trúc Này

### 1. Maintainability (Khả Năng Bảo Trì)
- Code rõ ràng, dễ hiểu
- Tách biệt rõ ràng các trách nhiệm
- Dễ dàng thêm tính năng mới

### 2. Testability (Khả Năng Kiểm Thử)
- Dễ dàng viết unit test
- Có thể mock các dependency
- Test coverage cao

### 3. Flexibility (Tính Linh Hoạt)
- Dễ dàng thay đổi triển khai
- Có thể mở rộng mà không sửa đổi code hiện tại
- Hỗ trợ nhiều loại database

### 4. Security (Bảo Mật)
- Validation nghiêm ngặt
- Kiểm soát truy cập tốt
- Bảo vệ dữ liệu nhạy cảm

### 5. Performance (Hiệu Suất)
- Tối ưu hóa truy vấn
- Caching hiệu quả
- Quản lý bộ nhớ tốt

## Kết Luận

Tầng domain này cung cấp một nền tảng vững chắc cho hệ thống quản lý phòng gym với:

- **Kiến trúc rõ ràng**: Tách biệt rõ ràng các trách nhiệm
- **Code chất lượng cao**: Tuân thủ các nguyên tắc SOLID và clean code
- **Dễ bảo trì**: Code dễ hiểu và dễ sửa đổi
- **Dễ kiểm thử**: Có thể test toàn diện
- **Linh hoạt**: Dễ dàng mở rộng và thay đổi

Đây là một ví dụ điển hình về cách triển khai domain layer trong một ứng dụng Java hiện đại, phù hợp cho việc học tập và phát triển thực tế.
