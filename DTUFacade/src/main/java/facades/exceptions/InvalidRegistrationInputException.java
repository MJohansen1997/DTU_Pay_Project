package facades.exceptions;

public class InvalidRegistrationInputException extends Exception {
    public InvalidRegistrationInputException(String type) {
        super(type);
    }
}
