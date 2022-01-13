package token.service;

public class ToManyTokensLeftException extends Exception {
    public ToManyTokensLeftException(String cause) {
        super(cause);
    }
}
