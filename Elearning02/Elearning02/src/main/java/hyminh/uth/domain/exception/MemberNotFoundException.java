package hyminh.uth.domain.exception;

/**
 * Exception thrown when a member is not found.
 * This exception is used to indicate that a requested member does not exist.
 * 
 * @author Gym Management System
 * @version 1.0
 */
public class MemberNotFoundException extends RuntimeException {
    
    /**
     * Constructs a new MemberNotFoundException with the specified detail message.
     * 
     * @param message the detail message
     */
    public MemberNotFoundException(String message) {
        super(message);
    }
    
    /**
     * Constructs a new MemberNotFoundException with the specified detail message and cause.
     * 
     * @param message the detail message
     * @param cause the cause of the exception
     */
    public MemberNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
