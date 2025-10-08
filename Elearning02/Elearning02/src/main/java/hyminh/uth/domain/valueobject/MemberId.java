package hyminh.uth.domain.valueobject;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Value object representing a unique member identifier.
 * This class ensures type safety and provides validation for member IDs.
 * 
 * @author Gym Management System
 * @version 1.0
 */
public final class MemberId {
    
    private static final Pattern MEMBER_ID_PATTERN = Pattern.compile("^MEM-\\d{6}$");
    private final String value;
    
    /**
     * Constructs a new MemberId with the specified value.
     * 
     * @param value the member ID value
     * @throws IllegalArgumentException if the value is invalid
     */
    public MemberId(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Member ID cannot be null or empty");
        }
        if (!MEMBER_ID_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("Member ID must follow format MEM-XXXXXX");
        }
        this.value = value;
    }
    
    /**
     * Creates a new MemberId from a numeric value.
     * 
     * @param numericValue the numeric value to convert
     * @return a new MemberId instance
     * @throws IllegalArgumentException if the numeric value is invalid
     */
    public static MemberId fromNumeric(int numericValue) {
        if (numericValue < 0 || numericValue > 999999) {
            throw new IllegalArgumentException("Numeric value must be between 0 and 999999");
        }
        return new MemberId(String.format("MEM-%06d", numericValue));
    }
    
    /**
     * Returns the string value of this MemberId.
     * 
     * @return the member ID value
     */
    public String getValue() {
        return value;
    }
    
    /**
     * Extracts the numeric part of the member ID.
     * 
     * @return the numeric part as an integer
     */
    public int getNumericValue() {
        return Integer.parseInt(value.substring(4));
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MemberId memberId = (MemberId) obj;
        return Objects.equals(value, memberId.value);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
    
    @Override
    public String toString() {
        return value;
    }
}
