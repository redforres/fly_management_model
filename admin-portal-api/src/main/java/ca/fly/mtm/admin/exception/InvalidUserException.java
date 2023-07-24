package ca.fly.mtm.admin.exception;

public class InvalidUserException extends Exception {
    /**
     * Constructs new InvalidUserException instance.
     */
    public InvalidUserException() {
        super();
    }

    /**
     * Constructs new InvalidUserException.
     *
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public InvalidUserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * Constructs new InvalidUserException.
     *
     * @param message
     * @param cause
     */
    public InvalidUserException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs new InvalidUserException.
     *
     * @param message
     */
    public InvalidUserException(String message) {
        super(message);
    }

    /**
     * Constructs new InvalidUserException.
     *
     * @param cause
     */
    public InvalidUserException(Throwable cause) {
        super(cause);
    }
}
