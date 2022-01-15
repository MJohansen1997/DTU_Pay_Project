package token.service.adapter;

import token.service.DTO.TokenList;
import token.service.exceptions.UserNotFoundException;
import token.service.port.ITokenRepository;

import java.util.ArrayList;
import java.util.HashMap;

public class TokenRepository implements ITokenRepository {
    private static final HashMap<String, TokenList> tokenMap = new HashMap<String, TokenList>(0);

    @Override
    public ArrayList<TokenList> getTokens() {
        return (ArrayList<TokenList>) tokenMap.values();
    }

    @Override
    public TokenList getTokensByUser(String userID) {
        return tokenMap.get(userID);
    }

    @Override
    public String getUserByToken(String tokenID) throws UserNotFoundException {
        for ( String userID : tokenMap.keySet() ) {
            if (tokenMap.get(userID).contains(tokenID))
                return userID;
        }
        throw new UserNotFoundException("Token owner was not found");
    }

    @Override
    public TokenList createTokenList(String userID) {
        TokenList temp = new TokenList();
        tokenMap.put(userID, temp);
        return temp;
    }

    @Override
    public TokenList updateTokenList(String userID, TokenList tokenList) {
        tokenMap.get(userID).setTokens(tokenList.getTokens());
        return tokenList;
    }

    @Override
    public TokenList removeTokenList(String userID) {
        TokenList temp = tokenMap.get(userID);
        tokenMap.remove(userID);
        return temp;
    }

    @Override
    public String consumeToken(String tokenID) throws UserNotFoundException {
        String userID = getUserByToken(tokenID);
        tokenMap.get(userID).getTokens().remove(tokenID);
        return userID;
    }
}
