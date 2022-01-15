package token.service;

import token.service.DTO.TokenList;
import token.service.adapter.TokenRepository;
import token.service.exceptions.InvalidTokenException;
import token.service.exceptions.ToManyTokensLeftException;
import token.service.exceptions.UserNotFoundException;
import token.service.port.ITokenRepository;
import token.service.port.ITokenService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class TokenService implements ITokenService {
    ITokenRepository repository = new TokenRepository();

    //default constructor
    public TokenService() {}

    @Override
    public TokenList createUser(String userID) {
        return repository.createTokenList(userID);
    }

    //This Method handles the creation and checking of Tokens and the rules behind them as such
    public TokenList requestNewSet(String userID) throws ToManyTokensLeftException, UserNotFoundException {
        TokenList temp = repository.getTokensByUser(userID);
        if (temp == null)
            throw new UserNotFoundException("Invalid User");
        //This method is used to make sure the user doesn't have more than the 1 allowed token left before requesting a new one
        //here we return a new list of Tokens
        return repository.updateTokenList(userID, generateNewSet(temp.getTokens().size()));
    }
    
    public String consumeToken(String token) throws InvalidTokenException{
        try {
            return repository.consumeToken(token);
        }catch (UserNotFoundException e) {
            throw new InvalidTokenException("Invalid token, no such token");
        }
    }

    // Inefficent since it has to run through whole hashMap for each call
    public TokenList getTokensFromUserID(String userID) throws UserNotFoundException {
        // If the userID is not contained in the register throw an exception
        return repository.getTokensByUser(userID);
    }

    //This method handles the responsibility of
    private TokenList generateNewSet(int tokensLeftOffSet) {
        TokenList list = new TokenList();
        String token;
        for (int i = 0; i < 6 - tokensLeftOffSet; i++) {
            String temp;
            do {
                token = generateRandomToken();
                try {
                    temp = repository.getUserByToken(token);
                }catch (Exception ignored){
                    temp = "";
                }
            }
            while (!temp.equals(""));
            list.getTokens().add(token);
        }
        return list;
    }

    //Taken from https://www.codegrepper.com/code-examples/java/java+generate+token+string
    private static String generateRandomToken() {
        int len = 20;
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk"
                +"lmnopqrstuvwxyz!@#$%&";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        return sb.toString();
    }
}
