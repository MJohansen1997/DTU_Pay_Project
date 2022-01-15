package token.service.port;

import token.service.DTO.TokenList;
import token.service.exceptions.InvalidTokenException;
import token.service.exceptions.ToManyTokensLeftException;
import token.service.exceptions.UserNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;

public interface ITokenService {
    TokenList createUser(String userID);
    TokenList requestNewSet(String userID) throws ToManyTokensLeftException, UserNotFoundException;
    String consumeToken(String token) throws InvalidTokenException, UserNotFoundException;
    TokenList getTokensFromUserID(String userID) throws UserNotFoundException;

}
