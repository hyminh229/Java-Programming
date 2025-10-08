package hyminh.uth.domain.service;

import hyminh.uth.domain.entity.Member;
import hyminh.uth.domain.repository.MemberRepository;
import hyminh.uth.domain.repository.SubscriptionRepository;
import hyminh.uth.domain.valueobject.MemberId;
import hyminh.uth.domain.exception.MemberNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for MemberService.
 * This class contains test cases for member service functionality.
 * 
 * @author Gym Management System
 * @version 1.0
 */
@DisplayName("Member Service Tests")
class MemberServiceTest {
    
    @Mock
    private MemberRepository memberRepository;
    
    @Mock
    private SubscriptionRepository subscriptionRepository;
    
    private MemberService memberService;
    private MemberId memberId;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        memberService = new MemberService(memberRepository, subscriptionRepository);
        memberId = new MemberId("MEM-000001");
    }
    
    @Test
    @DisplayName("Should create member successfully")
    void shouldCreateMemberSuccessfully() {
        // Given
        String userId = "USER-001";
        String username = "john_doe";
        String password = "password123";
        String email = "john@example.com";
        String phone = "+1234567890";
        
        when(memberRepository.existsByUserId(userId)).thenReturn(false);
        when(memberRepository.save(any(Member.class))).thenAnswer(invocation -> invocation.getArgument(0));
        
        // When
        Member result = memberService.createMember(userId, username, password, email, phone, memberId);
        
        // Then
        assertNotNull(result);
        assertEquals(userId, result.getUserId());
        assertEquals(username, result.getUsername());
        assertEquals(email, result.getEmail());
        assertEquals(phone, result.getPhone());
        assertEquals(memberId, result.getMemberId());
        
        verify(memberRepository).existsByUserId(userId);
        verify(memberRepository).save(any(Member.class));
    }
    
    @Test
    @DisplayName("Should throw exception when user ID already exists")
    void shouldThrowExceptionWhenUserIdAlreadyExists() {
        // Given
        String userId = "USER-001";
        String username = "john_doe";
        String password = "password123";
        String email = "john@example.com";
        String phone = "+1234567890";
        
        when(memberRepository.existsByUserId(userId)).thenReturn(true);
        
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            memberService.createMember(userId, username, password, email, phone, memberId);
        });
        
        verify(memberRepository).existsByUserId(userId);
        verify(memberRepository, never()).save(any(Member.class));
    }
    
    @Test
    @DisplayName("Should find member by ID")
    void shouldFindMemberById() {
        // Given
        Member member = new Member("USER-001", "john_doe", "password123", 
                                  "john@example.com", "+1234567890", memberId);
        
        when(memberRepository.findById(memberId)).thenReturn(java.util.Optional.of(member));
        
        // When
        Member result = memberService.findById(memberId);
        
        // Then
        assertNotNull(result);
        assertEquals(memberId, result.getMemberId());
        assertEquals("USER-001", result.getUserId());
        
        verify(memberRepository).findById(memberId);
    }
    
    @Test
    @DisplayName("Should throw exception when member not found by ID")
    void shouldThrowExceptionWhenMemberNotFoundById() {
        // Given
        when(memberRepository.findById(memberId)).thenReturn(java.util.Optional.empty());
        
        // When & Then
        assertThrows(MemberNotFoundException.class, () -> {
            memberService.findById(memberId);
        });
        
        verify(memberRepository).findById(memberId);
    }
    
    @Test
    @DisplayName("Should find member by user ID")
    void shouldFindMemberByUserId() {
        // Given
        String userId = "USER-001";
        Member member = new Member(userId, "john_doe", "password123", 
                                  "john@example.com", "+1234567890", memberId);
        
        when(memberRepository.findByUserId(userId)).thenReturn(java.util.Optional.of(member));
        
        // When
        Member result = memberService.findByUserId(userId);
        
        // Then
        assertNotNull(result);
        assertEquals(userId, result.getUserId());
        assertEquals(memberId, result.getMemberId());
        
        verify(memberRepository).findByUserId(userId);
    }
    
    @Test
    @DisplayName("Should throw exception when member not found by user ID")
    void shouldThrowExceptionWhenMemberNotFoundByUserId() {
        // Given
        String userId = "USER-001";
        when(memberRepository.findByUserId(userId)).thenReturn(java.util.Optional.empty());
        
        // When & Then
        assertThrows(MemberNotFoundException.class, () -> {
            memberService.findByUserId(userId);
        });
        
        verify(memberRepository).findByUserId(userId);
    }
    
    @Test
    @DisplayName("Should update progress successfully")
    void shouldUpdateProgressSuccessfully() {
        // Given
        Member member = new Member("USER-001", "john_doe", "password123", 
                                  "john@example.com", "+1234567890", memberId);
        double weight = 75.5;
        double bodyFat = 15.0;
        int workoutsCompleted = 10;
        
        when(memberRepository.findById(memberId)).thenReturn(java.util.Optional.of(member));
        when(memberRepository.save(any(Member.class))).thenAnswer(invocation -> invocation.getArgument(0));
        
        // When
        memberService.updateProgress(memberId, weight, bodyFat, workoutsCompleted);
        
        // Then
        assertEquals(weight, member.getProgressMetrics().getWeight());
        assertEquals(bodyFat, member.getProgressMetrics().getBodyFat());
        assertEquals(workoutsCompleted, member.getProgressMetrics().getWorkoutsCompleted());
        
        verify(memberRepository).findById(memberId);
        verify(memberRepository).save(member);
    }
    
    @Test
    @DisplayName("Should increment workouts successfully")
    void shouldIncrementWorkoutsSuccessfully() {
        // Given
        Member member = new Member("USER-001", "john_doe", "password123", 
                                  "john@example.com", "+1234567890", memberId);
        int initialWorkouts = member.getProgressMetrics().getWorkoutsCompleted();
        
        when(memberRepository.findById(memberId)).thenReturn(java.util.Optional.of(member));
        when(memberRepository.save(any(Member.class))).thenAnswer(invocation -> invocation.getArgument(0));
        
        // When
        memberService.incrementWorkouts(memberId);
        
        // Then
        assertEquals(initialWorkouts + 1, member.getProgressMetrics().getWorkoutsCompleted());
        
        verify(memberRepository).findById(memberId);
        verify(memberRepository).save(member);
    }
    
    @Test
    @DisplayName("Should get total member count")
    void shouldGetTotalMemberCount() {
        // Given
        long expectedCount = 10L;
        when(memberRepository.count()).thenReturn(expectedCount);
        
        // When
        long result = memberService.getTotalMemberCount();
        
        // Then
        assertEquals(expectedCount, result);
        verify(memberRepository).count();
    }
    
    @Test
    @DisplayName("Should get active subscription count")
    void shouldGetActiveSubscriptionCount() {
        // Given
        long expectedCount = 8L;
        when(memberRepository.countWithActiveSubscriptions()).thenReturn(expectedCount);
        
        // When
        long result = memberService.getActiveSubscriptionCount();
        
        // Then
        assertEquals(expectedCount, result);
        verify(memberRepository).countWithActiveSubscriptions();
    }
    
    @Test
    @DisplayName("Should calculate member retention rate")
    void shouldCalculateMemberRetentionRate() {
        // Given
        long totalMembers = 100L;
        long activeSubscriptions = 75L;
        double expectedRate = 75.0;
        
        when(memberRepository.count()).thenReturn(totalMembers);
        when(memberRepository.countWithActiveSubscriptions()).thenReturn(activeSubscriptions);
        
        // When
        double result = memberService.getMemberRetentionRate();
        
        // Then
        assertEquals(expectedRate, result, 0.01);
        verify(memberRepository).count();
        verify(memberRepository).countWithActiveSubscriptions();
    }
    
    @Test
    @DisplayName("Should return zero retention rate when no members")
    void shouldReturnZeroRetentionRateWhenNoMembers() {
        // Given
        when(memberRepository.count()).thenReturn(0L);
        
        // When
        double result = memberService.getMemberRetentionRate();
        
        // Then
        assertEquals(0.0, result, 0.01);
        verify(memberRepository).count();
        verify(memberRepository, never()).countWithActiveSubscriptions();
    }
}
