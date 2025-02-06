package seedu.donk;

/**
 * Represents an exception specific to the Donk chatbot application.
 * This exception is thrown when an error occurs during user input processing or task operations.
 */
public class DonkException extends Exception {

    /**
     * Constructs a {@code DonkException} with a specified error message.
     *
     * @param message The detailed error message describing the cause of the exception.
     */
    public DonkException(String message) {
        super(message);
    }
}
