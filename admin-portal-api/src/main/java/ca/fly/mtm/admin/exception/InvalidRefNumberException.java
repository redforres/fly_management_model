package ca.fly.mtm.admin.exception;

public class InvalidRefNumberException extends Exception {
    /**
     * Constructs new InvalidRefNumberException instance.
     */
    public InvalidRefNumberException() {
        super();
    }

    /**
     * Constructs new InvalidRefNumberException.
     *
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public InvalidRefNumberException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * Constructs new InvalidRefNumberException.
     *
     * @param message
     * @param cause
     */
    public InvalidRefNumberException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs new InvalidRefNumberException.
     *
     * @param message
     */
    public InvalidRefNumberException(String message) {
        super(message);
    }

    /**
     * Constructs new InvalidRefNumberException.
     *
     * @param cause
     */
    public InvalidRefNumberException(Throwable cause) {
        super(cause);
    }
}
