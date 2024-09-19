/**
 * Project 4 - InvalidMazeException
 *
 * Provides an exception to be thrown
 * if the given maze is invalid.
 * 
 * @author Brady Williams, L23
 *
 * @version April 5, 2024
 *
 */
public class InvalidMazeException extends Exception {
    public InvalidMazeException(String message) {
        super(message);
    }
}