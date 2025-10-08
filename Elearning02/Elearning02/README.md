# Gym Management System - Domain Layer

This project implements a comprehensive domain layer for a gym management system following SOLID principles, clean code practices, and rich domain models.

## Architecture Overview

The domain layer is organized into the following packages:

```
src/main/java/hyminh/uth/domain/
├── entity/           # Domain entities with business logic
├── valueobject/      # Immutable value objects
├── repository/       # Repository interfaces
├── repository/impl/  # In-memory repository implementations
├── service/          # Domain services
├── dto/              # Data Transfer Objects
└── exception/        # Domain-specific exceptions
```

## Domain Entities

### Core Entities

1. **User** (Abstract Base Class)
   - Base class for all user types
   - Handles authentication and common user properties
   - Implements validation and business rules

2. **Member** (Extends User)
   - Represents gym members
   - Manages subscription and progress tracking
   - Handles workout schedules and attendance

3. **Trainer** (Extends User)
   - Represents gym trainers
   - Manages member assignments and specializations
   - Tracks experience and availability

4. **Admin** (Extends User)
   - Represents system administrators
   - Manages permissions and system access
   - Handles administrative operations

5. **Subscription**
   - Represents member subscriptions
   - Manages subscription lifecycle and status
   - Handles renewal and cancellation logic

6. **Exercise**
   - Represents workout exercises
   - Manages exercise properties and difficulty
   - Handles equipment and muscle group targeting

## Value Objects

### User-Related Value Objects

- **UserRole**: Enum representing user roles (ADMIN, TRAINER, MEMBER)
- **MemberId**: Unique identifier for members
- **AdminAction**: Enum representing admin actions
- **Specialization**: Enum representing trainer specializations

### Subscription-Related Value Objects

- **SubscriptionStatus**: Enum representing subscription statuses
- **SubscriptionPlan**: Represents subscription plans with features
- **PlanType**: Enum representing plan types (BASIC, STANDARD, PREMIUM, VIP)

### Exercise-Related Value Objects

- **ExerciseType**: Enum representing exercise types
- **DifficultyLevel**: Enum representing difficulty levels
- **ProgressMetrics**: Represents member progress data

## Repository Interfaces

### UserRepository
- Manages user data access operations
- Supports finding by ID, username, email, and role
- Handles user activation/deactivation

### MemberRepository
- Manages member-specific data access
- Supports finding by registration date, subscription status
- Handles trainer assignments and progress tracking

### SubscriptionRepository
- Manages subscription data access
- Supports finding by status, date ranges
- Handles revenue calculations

### ExerciseRepository
- Manages exercise data access
- Supports finding by type, difficulty, equipment
- Handles search and filtering operations

## Domain Services

### MemberService
- Encapsulates member-related business logic
- Handles member creation, progress updates
- Manages subscription assignments
- Calculates retention rates and statistics

## Data Transfer Objects (DTOs)

### MemberDTO
- Represents member data for transfer between layers
- Includes subscription and progress information
- Provides calculated properties like attendance rate

### ExerciseDTO
- Represents exercise data for transfer
- Includes exercise properties and calculations
- Supports suitability checks for different user levels

## Exceptions

### Domain Exceptions
- **MemberNotFoundException**: Thrown when a member is not found
- **SubscriptionNotFoundException**: Thrown when a subscription is not found
- **InvalidSubscriptionException**: Thrown when subscription operations are invalid

## Key Features

### Rich Domain Models
- Entities contain business logic and validation
- Value objects ensure type safety and immutability
- Domain services encapsulate complex business operations

### SOLID Principles
- **Single Responsibility**: Each class has one reason to change
- **Open/Closed**: Open for extension, closed for modification
- **Liskov Substitution**: Subtypes are substitutable for base types
- **Interface Segregation**: Clients depend only on interfaces they use
- **Dependency Inversion**: Depend on abstractions, not concretions

### Clean Code Practices
- Comprehensive Javadoc documentation
- Meaningful method and variable names
- Proper error handling and validation
- Immutable value objects where appropriate

### Comprehensive Testing
- Unit tests for entities and services
- Mock-based testing for service layer
- Test coverage for edge cases and error conditions

## Usage Examples

### Creating a Member
```java
MemberId memberId = new MemberId("MEM-000001");
Member member = new Member("USER-001", "john_doe", "password123", 
                          "john@example.com", "+1234567890", memberId);
```

### Creating a Subscription Plan
```java
SubscriptionPlan plan = SubscriptionPlan.createPremium("PLAN-001", "Premium Plan", 
                                                      12, 99.99);
```

### Creating an Exercise
```java
Exercise exercise = Exercise.createSimple("EX-001", "Push-ups", 
                                         ExerciseType.STRENGTH, DifficultyLevel.BEGINNER,
                                         "Upper body strength exercise", 
                                         "Chest, Shoulders, Triceps", "None");
```

### Using Domain Services
```java
MemberService memberService = new MemberService(memberRepository, subscriptionRepository);
Member member = memberService.createMember("USER-001", "john_doe", "password123", 
                                          "john@example.com", "+1234567890", memberId);
memberService.updateProgress(memberId, 75.5, 15.0, 10);
```

## Testing

The project includes comprehensive unit tests:

- **Entity Tests**: Test business logic and validation
- **Service Tests**: Test service layer with mocked dependencies
- **Value Object Tests**: Test immutability and equality
- **Repository Tests**: Test data access operations

Run tests with:
```bash
mvn test
```

## Dependencies

- Java 11+
- JUnit 5 for testing
- Mockito for mocking
- Maven for build management

## Design Patterns Used

- **Repository Pattern**: Abstracts data access
- **Value Object Pattern**: Immutable objects for data
- **Service Layer Pattern**: Encapsulates business logic
- **DTO Pattern**: Transfers data between layers
- **Factory Pattern**: Creates complex objects
- **Strategy Pattern**: Handles different user types

## Future Enhancements

- Add more domain services (TrainerService, AdminService)
- Implement additional value objects
- Add more comprehensive validation rules
- Extend repository interfaces with more query methods
- Add domain events for better decoupling

## Contributing

When contributing to this project:

1. Follow SOLID principles
2. Write comprehensive tests
3. Add proper Javadoc documentation
4. Ensure all code compiles without warnings
5. Follow the existing code structure and naming conventions
