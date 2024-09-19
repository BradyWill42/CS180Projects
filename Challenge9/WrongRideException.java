
/**
 * HW-09 -- WrongRideException
 *
 * This exception is thrown when
 * their the wrong type of ride has
 * been added to a park
 *
 * @author Brady Williams, L23
 *
 * @version Mar 16, 2024
 *
 */
public class WrongRideException extends Exception {
    public WrongRideException(String message) {
        super(message);
    }
}