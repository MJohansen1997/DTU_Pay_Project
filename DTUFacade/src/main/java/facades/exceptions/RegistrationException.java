package facades.exceptions;

public class RegistrationException extends Exception {
    public RegistrationException(String type) {
        super(type);
    }
}
