package token.service.exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String cause) {
        super(cause);
    }
}
