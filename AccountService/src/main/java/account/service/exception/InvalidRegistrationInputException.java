package account.service.exception;

public class InvalidRegistrationInputException extends RepositoryException{
    public InvalidRegistrationInputException(String message) {
        super(message);
    }
}
