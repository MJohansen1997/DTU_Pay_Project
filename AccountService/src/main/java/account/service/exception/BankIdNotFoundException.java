package account.service.exception;

public class BankIdNotFoundException extends RepositoryException{
    public BankIdNotFoundException(String message) {
        super(message);
    }
}
