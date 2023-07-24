package ca.fly.mtm.admin.exception;

public class InvalidOfficeException extends Exception {
    /**
     * Constructs new InvalidOfficeException instance.
     */
    public InvalidOfficeException() {
        super();
    }

    /**
     * Constructs new InvalidOfficeException.
     *
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public InvalidOfficeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * Constructs new InvalidOfficeException.
     *
     * @param message
     * @param cause
     */
    public InvalidOfficeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs new InvalidOfficeException.
     *
     * @param message
     */
    public InvalidOfficeException(String message) {
        super(message);
    }

    /**
     * Constructs new InvalidOfficeException.
     *
     * @param cause
     */
    public InvalidOfficeException(Throwable cause) {
        super(cause);
    }
}
