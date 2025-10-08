package hyminh.uth.domain.exception;

/**
 * Exception thrown when a subscription is not found.
 * This exception is used to indicate that a requested subscription does not exist.
 * 
 * @author Gym Management System
 * @version 1.0
 */
public class SubscriptionNotFoundException extends RuntimeException {
    
    /**
     * Constructs a new SubscriptionNotFoundException with the specified detail message.
     * 
     * @param message the detail message
     */
    public SubscriptionNotFoundException(String message) {
        super(message);
    }
    
    /**
     * Constructs a new SubscriptionNotFoundException with the specified detail message and cause.
     * 
     * @param message the detail message
     * @param cause the cause of the exception
     */
    public SubscriptionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
