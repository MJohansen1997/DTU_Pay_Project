package account.service.exception;

public class BankIdAlreadyRegisteredException extends RepositoryException {
    public BankIdAlreadyRegisteredException(String message) {
        super(message);
    }
    public String toString() {
        return ("BankIdNotFound");
    }
}
