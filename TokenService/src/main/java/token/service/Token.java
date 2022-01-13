package token.service;

public class Token {
    String TokenID;
    Boolean expired;

    public String getTokenID() {
        return TokenID;
    }

    public void setTokenID(String tokenID) {
        TokenID = tokenID;
    }

}
