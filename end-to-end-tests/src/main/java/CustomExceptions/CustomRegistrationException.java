package CustomExceptions;

public class CustomRegistrationException extends Exception {

    public CustomRegistrationException(String wrong) {
        super(wrong);
    }

}
