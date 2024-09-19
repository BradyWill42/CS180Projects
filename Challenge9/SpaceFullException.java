/**
 * HW-09 -- SpaceFullException
 *
 * This exception is thrown when
 * their is no more space left in a park
 *
 * @author Brady Williams, L23
 *
 * @version Mar 16, 2024
 *
 */
public class SpaceFullException extends Exception {
    public SpaceFullException(String message) {
        super(message);
    }
}