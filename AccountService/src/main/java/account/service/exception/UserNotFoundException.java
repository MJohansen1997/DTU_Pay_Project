package account.service.exception;

public class UserNotFoundException extends RepositoryException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
