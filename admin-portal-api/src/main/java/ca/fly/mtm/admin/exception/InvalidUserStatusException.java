package ca.fly.mtm.admin.exception;

public class InvalidUserStatusException extends Exception {
    /**
     * Constructs new InvalidUserStatusException instance.
     */
    public InvalidUserStatusException() {
        super();
    }

    /**
     * Constructs new InvalidUserStatusException.
     *
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public InvalidUserStatusException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * Constructs new InvalidUserStatusException.
     *
     * @param message
     * @param cause
     */
    public InvalidUserStatusException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs new InvalidUserStatusException.
     *
     * @param message
     */
    public InvalidUserStatusException(String message) {
        super(message);
    }

    /**
     * Constructs new InvalidUserStatusException.
     *
     * @param cause
     */
    public InvalidUserStatusException(Throwable cause) {
        super(cause);
    }
}
