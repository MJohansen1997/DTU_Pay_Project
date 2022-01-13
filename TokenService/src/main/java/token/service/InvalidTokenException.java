package token.service;

public class InvalidTokenException extends Exception {
    public InvalidTokenException(String cause) {
        super(cause);
    }
    
}
