package hyminh.uth.domain.demo;

import hyminh.uth.domain.entity.*;
import hyminh.uth.domain.valueobject.*;
import hyminh.uth.domain.repository.*;
import hyminh.uth.domain.repository.impl.*;
import hyminh.uth.domain.service.MemberService;
import hyminh.uth.domain.dto.MemberDTO;
import java.time.LocalDate;

/**
 * Demo class để minh họa cách sử dụng tầng domain.
 * Lớp này cho thấy cách tạo và sử dụng các thành phần domain.
 * 
 * @author Gym Management System
 * @version 1.0
 */
public class DomainLayerDemo {
    
    public static void main(String[] args) {
        System.out.println("=== DEMO TẦNG DOMAIN - HỆ THỐNG QUẢN LÝ PHÒNG GYM ===\n");
        
        // 1. Khởi tạo repositories
        System.out.println("1. Khởi tạo repositories...");
        UserRepository userRepository = new InMemoryUserRepository();
        MemberRepository memberRepository = new InMemoryMemberRepository();
        SubscriptionRepository subscriptionRepository = new InMemorySubscriptionRepository();
        ExerciseRepository exerciseRepository = new InMemoryExerciseRepository();
        
        // 2. Khởi tạo domain service
        System.out.println("2. Khởi tạo domain service...");
        MemberService memberService = new MemberService(memberRepository, subscriptionRepository);
        
        // 3. Tạo các value objects
        System.out.println("3. Tạo các value objects...");
        MemberId memberId = new MemberId("MEM-000001");
        SubscriptionPlan premiumPlan = SubscriptionPlan.createPremium("PLAN-002", "Premium Plan", 12, 299.99);
        
        // 4. Tạo entities
        System.out.println("4. Tạo entities...");
        
        // Tạo Member
        Member member = new Member("USER-001", "john_doe", "password123", 
                                  "john@example.com", "+1234567890", memberId);
        System.out.println("   ✓ Tạo Member: " + member.getUsername());
        
        // Tạo Trainer
        Trainer trainer = new Trainer("USER-002", "jane_trainer", "password456", 
                                     "jane@example.com", "+0987654321", 
                                     Specialization.STRENGTH, 5);
        System.out.println("   ✓ Tạo Trainer: " + trainer.getUsername() + 
                          " (Chuyên môn: " + trainer.getSpecialization() + ")");
        
        // Tạo Admin
        Admin admin = new Admin("USER-003", "admin_user", "admin123", 
                               "admin@example.com", "+1111111111", "SUPER_ADMIN");
        System.out.println("   ✓ Tạo Admin: " + admin.getUsername() + 
                          " (Cấp: " + admin.getAdminLevel() + ")");
        
        // 5. Tạo Exercise
        System.out.println("5. Tạo Exercise...");
        Exercise exercise = Exercise.createSimple("EX-001", "Push-ups", 
                                                 ExerciseType.STRENGTH, DifficultyLevel.BEGINNER,
                                                 "Upper body strength exercise", 
                                                 "Chest, Shoulders, Triceps", "None");
        System.out.println("   ✓ Tạo Exercise: " + exercise.getName() + 
                          " (Loại: " + exercise.getType() + ", Độ khó: " + exercise.getDifficulty() + ")");
        
        // 6. Tạo Subscription
        System.out.println("6. Tạo Subscription...");
        Subscription subscription = new Subscription("SUB-001", premiumPlan, LocalDate.now());
        System.out.println("   ✓ Tạo Subscription: " + subscription.getSubscriptionId() + 
                          " (Gói: " + subscription.getPlan().getPlanName() + 
                          ", Trạng thái: " + subscription.getStatus() + ")");
        
        // 7. Lưu vào repositories
        System.out.println("7. Lưu vào repositories...");
        userRepository.save(member);
        userRepository.save(trainer);
        userRepository.save(admin);
        memberRepository.save(member);
        subscriptionRepository.save(subscription);
        exerciseRepository.save(exercise);
        System.out.println("   ✓ Đã lưu tất cả entities vào repositories");
        
        // 8. Sử dụng domain service
        System.out.println("8. Sử dụng domain service...");
        
        // Gán gói tập cho member
        memberService.assignSubscription(memberId, "SUB-001");
        System.out.println("   ✓ Đã gán gói tập cho member");
        
        // Cập nhật tiến độ
        memberService.updateProgress(memberId, 75.5, 15.0, 10);
        System.out.println("   ✓ Đã cập nhật tiến độ: Cân nặng 75.5kg, Mỡ 15%, 10 buổi tập");
        
        // Tăng số buổi tập
        memberService.incrementWorkouts(memberId);
        System.out.println("   ✓ Đã tăng số buổi tập lên 11");
        
        // 9. Hiển thị thông tin
        System.out.println("9. Hiển thị thông tin...");
        
        // Thông tin Member
        Member updatedMember = memberService.findById(memberId);
        System.out.println("   Member Info:");
        System.out.println("     - ID: " + updatedMember.getMemberId());
        System.out.println("     - Username: " + updatedMember.getUsername());
        System.out.println("     - Email: " + updatedMember.getEmail());
        System.out.println("     - Có gói tập: " + updatedMember.hasActiveSubscription());
        System.out.println("     - Cân nặng: " + updatedMember.getProgressMetrics().getWeight() + "kg");
        System.out.println("     - Tỷ lệ mỡ: " + updatedMember.getProgressMetrics().getBodyFat() + "%");
        System.out.println("     - Số buổi tập: " + updatedMember.getProgressMetrics().getWorkoutsCompleted());
        System.out.println("     - Thời gian thành viên: " + updatedMember.getMembershipDurationInDays() + " ngày");
        
        // Thông tin Trainer
        System.out.println("   Trainer Info:");
        System.out.println("     - Username: " + trainer.getUsername());
        System.out.println("     - Chuyên môn: " + trainer.getSpecialization());
        System.out.println("     - Kinh nghiệm: " + trainer.getYearsOfExperience() + " năm");
        System.out.println("     - Cấp độ: " + trainer.getExperienceLevel());
        System.out.println("     - Sẵn sàng: " + trainer.isAvailable());
        
        // Thông tin Admin
        System.out.println("   Admin Info:");
        System.out.println("     - Username: " + admin.getUsername());
        System.out.println("     - Cấp: " + admin.getAdminLevel());
        System.out.println("     - Quyền quản lý user: " + admin.canManageUsers());
        System.out.println("     - Quyền quản lý gói tập: " + admin.canManageSubscriptions());
        System.out.println("     - Quyền đầy đủ: " + admin.hasFullPrivileges());
        
        // Thông tin Exercise
        System.out.println("   Exercise Info:");
        System.out.println("     - Tên: " + exercise.getName());
        System.out.println("     - Loại: " + exercise.getType());
        System.out.println("     - Độ khó: " + exercise.getDifficulty());
        System.out.println("     - Số set: " + exercise.getDefaultSets());
        System.out.println("     - Số rep: " + exercise.getDefaultReps());
        System.out.println("     - Phù hợp cho người mới: " + exercise.getDifficulty().isSuitableForBeginners());
        System.out.println("     - Có thể tập tại nhà: " + exercise.getType().canBeDoneAtHome());
        
        // 10. Thống kê
        System.out.println("10. Thống kê...");
        System.out.println("   - Tổng số user: " + userRepository.count());
        System.out.println("   - Tổng số member: " + memberRepository.count());
        System.out.println("   - Tổng số gói tập: " + subscriptionRepository.count());
        System.out.println("   - Tổng số bài tập: " + exerciseRepository.count());
        System.out.println("   - Member có gói tập: " + memberService.getActiveSubscriptionCount());
        System.out.println("   - Tỷ lệ duy trì: " + String.format("%.1f", memberService.getMemberRetentionRate()) + "%");
        
        // 11. Demo DTO
        System.out.println("11. Demo DTO...");
        MemberDTO memberDTO = new MemberDTO(
            updatedMember.getUserId(),
            updatedMember.getUsername(),
            updatedMember.getEmail(),
            updatedMember.getPhone(),
            updatedMember.getRole(),
            updatedMember.getMemberId(),
            updatedMember.getRegistrationDate(),
            updatedMember.getCurrentSubscription() != null ? updatedMember.getCurrentSubscription().getSubscriptionId() : null,
            updatedMember.getCurrentSubscription() != null ? updatedMember.getCurrentSubscription().getStatus() : null,
            updatedMember.getCurrentSubscription() != null ? updatedMember.getCurrentSubscription().getStartDate() : null,
            updatedMember.getCurrentSubscription() != null ? updatedMember.getCurrentSubscription().getEndDate() : null,
            updatedMember.getProgressMetrics().getWeight(),
            updatedMember.getProgressMetrics().getBodyFat(),
            updatedMember.getProgressMetrics().getWorkoutsCompleted(),
            updatedMember.getWorkoutScheduleIds().size(),
            updatedMember.getAttendanceIds().size(),
            updatedMember.isActive(),
            updatedMember.getCreatedAt(),
            updatedMember.getLastModifiedAt()
        );
        
        System.out.println("   MemberDTO:");
        System.out.println("     - Tỷ lệ tham gia: " + String.format("%.1f", memberDTO.getAttendanceRate()) + "%");
        System.out.println("     - Có gói tập: " + memberDTO.hasActiveSubscription());
        System.out.println("     - Thời gian thành viên: " + memberDTO.getMembershipDurationInDays() + " ngày");
        
        System.out.println("\n=== DEMO HOÀN THÀNH ===");
        System.out.println("Tầng domain đã được triển khai thành công với đầy đủ các tính năng:");
        System.out.println("✓ Entities với logic nghiệp vụ phong phú");
        System.out.println("✓ Value objects bất biến và an toàn");
        System.out.println("✓ Repositories với triển khai trong bộ nhớ");
        System.out.println("✓ Domain services đóng gói logic phức tạp");
        System.out.println("✓ DTOs cho việc chuyển dữ liệu");
        System.out.println("✓ Exception handling toàn diện");
        System.out.println("✓ Validation nghiêm ngặt");
        System.out.println("✓ Tuân thủ nguyên tắc SOLID");
        System.out.println("✓ Clean code với comments tiếng Việt chi tiết");
    }
}
