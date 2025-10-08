package hyminh.uth.domain.exception;

/**
 * Exception thrown when a subscription operation is invalid.
 * This exception is used to indicate that a subscription operation cannot be performed.
 * 
 * @author Gym Management System
 * @version 1.0
 */
public class InvalidSubscriptionException extends RuntimeException {
    
    /**
     * Constructs a new InvalidSubscriptionException with the specified detail message.
     * 
     * @param message the detail message
     */
    public InvalidSubscriptionException(String message) {
        super(message);
    }
    
    /**
     * Constructs a new InvalidSubscriptionException with the specified detail message and cause.
     * 
     * @param message the detail message
     * @param cause the cause of the exception
     */
    public InvalidSubscriptionException(String message, Throwable cause) {
        super(message, cause);
    }
}
