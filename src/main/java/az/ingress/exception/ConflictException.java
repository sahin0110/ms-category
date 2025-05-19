package az.ingress.exception;

public class ConflictException extends RuntimeException {
    public ConflictException(String message, Object... args) {
        super(message.formatted(args));
    }
}
