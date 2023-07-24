package ca.fly.mtm.admin.exception;

public class InvalidRoleException extends Exception {
    /**
     * Constructs new InvalidRoleException instance.
     */
    public InvalidRoleException() {
        super();
    }

    /**
     * Constructs new InvalidRoleException.
     *
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public InvalidRoleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * Constructs new InvalidRoleException.
     *
     * @param message
     * @param cause
     */
    public InvalidRoleException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs new InvalidRoleException.
     *
     * @param message
     */
    public InvalidRoleException(String message) {
        super(message);
    }

    /**
     * Constructs new InvalidRoleException.
     *
     * @param cause
     */
    public InvalidRoleException(Throwable cause) {
        super(cause);
    }
}
