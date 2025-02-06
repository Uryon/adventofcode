package sopra.advent.utils;

public class AdventException extends RuntimeException {
    public AdventException(String message) {
        super(message);
    }

    public AdventException(String message, Throwable cause) {
        super(message, cause);
    }
}