package rnd.dev.error.exception;

public class Sha256Exception extends RuntimeException {

    public Sha256Exception() {
    }

    public Sha256Exception(String message) {
        super(message);
    }

    public Sha256Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public Sha256Exception(Throwable cause) {
        super(cause);
    }

    public Sha256Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
