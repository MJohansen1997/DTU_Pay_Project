package token.service;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String cause) {
        super(cause);
    }
}
