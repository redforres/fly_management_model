package ca.fly.mtm.admin.exception;

public class InvalidUserRoleException extends Exception {
    /**
     * Constructs new InvalidUserRoleException instance.
     */
    public InvalidUserRoleException() {
        super();
    }

    /**
     * Constructs new InvalidUserRoleException.
     *
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public InvalidUserRoleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * Constructs new InvalidUserRoleException.
     *
     * @param message
     * @param cause
     */
    public InvalidUserRoleException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs new InvalidUserRoleException.
     *
     * @param message
     */
    public InvalidUserRoleException(String message) {
        super(message);
    }

    /**
     * Constructs new InvalidUserRoleException.
     *
     * @param cause
     */
    public InvalidUserRoleException(Throwable cause) {
        super(cause);
    }
}
