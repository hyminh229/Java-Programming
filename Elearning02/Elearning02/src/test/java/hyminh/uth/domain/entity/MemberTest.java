package hyminh.uth.domain.entity;

import hyminh.uth.domain.valueobject.MemberId;
import hyminh.uth.domain.valueobject.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Member entity.
 * This class contains test cases for member-related functionality.
 * 
 * @author Gym Management System
 * @version 1.0
 */
@DisplayName("Member Entity Tests")
class MemberTest {
    
    private Member member;
    private MemberId memberId;
    
    @BeforeEach
    void setUp() {
        memberId = new MemberId("MEM-000001");
        member = new Member("USER-001", "john_doe", "password123", 
                           "john@example.com", "+1234567890", memberId);
    }
    
    @Test
    @DisplayName("Should create member with valid parameters")
    void shouldCreateMemberWithValidParameters() {
        // Given
        String userId = "USER-002";
        String username = "jane_doe";
        String password = "password456";
        String email = "jane@example.com";
        String phone = "+0987654321";
        MemberId newMemberId = new MemberId("MEM-000002");
        
        // When
        Member newMember = new Member(userId, username, password, email, phone, newMemberId);
        
        // Then
        assertNotNull(newMember);
        assertEquals(userId, newMember.getUserId());
        assertEquals(username, newMember.getUsername());
        assertEquals(email, newMember.getEmail());
        assertEquals(phone, newMember.getPhone());
        assertEquals(UserRole.MEMBER, newMember.getRole());
        assertEquals(newMemberId, newMember.getMemberId());
        assertTrue(newMember.isActive());
    }
    
    @Test
    @DisplayName("Should throw exception when member ID is null")
    void shouldThrowExceptionWhenMemberIdIsNull() {
        // Given
        String userId = "USER-003";
        String username = "test_user";
        String password = "password789";
        String email = "test@example.com";
        String phone = "+1111111111";
        
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            new Member(userId, username, password, email, phone, null);
        });
    }
    
    @Test
    @DisplayName("Should update progress metrics")
    void shouldUpdateProgressMetrics() {
        // Given
        double weight = 75.5;
        double bodyFat = 15.0;
        int workoutsCompleted = 10;
        
        // When
        member.updateProgress(weight, bodyFat, workoutsCompleted);
        
        // Then
        assertEquals(weight, member.getProgressMetrics().getWeight());
        assertEquals(bodyFat, member.getProgressMetrics().getBodyFat());
        assertEquals(workoutsCompleted, member.getProgressMetrics().getWorkoutsCompleted());
    }
    
    @Test
    @DisplayName("Should throw exception when weight is negative")
    void shouldThrowExceptionWhenWeightIsNegative() {
        // Given
        double negativeWeight = -10.0;
        double bodyFat = 15.0;
        int workoutsCompleted = 10;
        
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            member.updateProgress(negativeWeight, bodyFat, workoutsCompleted);
        });
    }
    
    @Test
    @DisplayName("Should throw exception when body fat is invalid")
    void shouldThrowExceptionWhenBodyFatIsInvalid() {
        // Given
        double weight = 75.5;
        double invalidBodyFat = 150.0; // > 100%
        int workoutsCompleted = 10;
        
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            member.updateProgress(weight, invalidBodyFat, workoutsCompleted);
        });
    }
    
    @Test
    @DisplayName("Should increment workouts")
    void shouldIncrementWorkouts() {
        // Given
        int initialWorkouts = member.getProgressMetrics().getWorkoutsCompleted();
        
        // When
        member.incrementWorkouts();
        
        // Then
        assertEquals(initialWorkouts + 1, member.getProgressMetrics().getWorkoutsCompleted());
    }
    
    @Test
    @DisplayName("Should add workout schedule")
    void shouldAddWorkoutSchedule() {
        // Given
        String scheduleId = "SCHEDULE-001";
        
        // When
        member.addWorkoutSchedule(scheduleId);
        
        // Then
        assertTrue(member.getWorkoutScheduleIds().contains(scheduleId));
    }
    
    @Test
    @DisplayName("Should throw exception when schedule ID is null")
    void shouldThrowExceptionWhenScheduleIdIsNull() {
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            member.addWorkoutSchedule(null);
        });
    }
    
    @Test
    @DisplayName("Should add attendance")
    void shouldAddAttendance() {
        // Given
        String attendanceId = "ATTENDANCE-001";
        
        // When
        member.addAttendance(attendanceId);
        
        // Then
        assertTrue(member.getAttendanceIds().contains(attendanceId));
    }
    
    @Test
    @DisplayName("Should throw exception when attendance ID is null")
    void shouldThrowExceptionWhenAttendanceIdIsNull() {
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            member.addAttendance(null);
        });
    }
    
    @Test
    @DisplayName("Should calculate membership duration")
    void shouldCalculateMembershipDuration() {
        // When
        long duration = member.getMembershipDurationInDays();
        
        // Then
        assertTrue(duration >= 0);
    }
    
    @Test
    @DisplayName("Should calculate attendance rate")
    void shouldCalculateAttendanceRate() {
        // Given
        member.addWorkoutSchedule("SCHEDULE-001");
        member.addWorkoutSchedule("SCHEDULE-002");
        member.addAttendance("ATTENDANCE-001");
        
        // When
        double attendanceRate = member.getAttendanceRate();
        
        // Then
        assertEquals(50.0, attendanceRate, 0.01);
    }
    
    @Test
    @DisplayName("Should return zero attendance rate when no schedules")
    void shouldReturnZeroAttendanceRateWhenNoSchedules() {
        // When
        double attendanceRate = member.getAttendanceRate();
        
        // Then
        assertEquals(0.0, attendanceRate, 0.01);
    }
}
