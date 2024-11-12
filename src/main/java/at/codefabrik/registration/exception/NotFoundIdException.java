package at.codefabrik.registration.exception;

public class NotFoundIdException extends RuntimeException {
    public NotFoundIdException(String message) {
        super(message);
    }
}
