package facades.exceptions;

public class ToManyTokensLeftException extends Exception {
    public ToManyTokensLeftException(String cause) {
        super(cause);
    }
}
