package token.service;

import token.service.DTO.TokenList;
import token.service.exceptions.InvalidTokenException;
import token.service.exceptions.ToManyTokensLeftException;
import token.service.exceptions.UserNotFoundException;
import token.service.port.ITokenRepository;
import token.service.port.ITokenService;

import java.util.Random;

public class TokenService implements ITokenService {
    private final ITokenRepository repository;

    //default constructor
    public TokenService(ITokenRepository repository) {
        this.repository = repository;
    }

    @Override
    public TokenList createUser(String userID) {
        return repository.createTokenList(userID);
    }

    //This Method handles the creation and checking of Tokens and the rules behind them as such
    public TokenList requestNewSet(String userID) throws ToManyTokensLeftException, UserNotFoundException {
        TokenList temp = repository.getTokensByUser(userID);
        if (temp == null)
            throw new UserNotFoundException("Invalid User");
        if (temp.getTokens().size() > 1)
            throw new ToManyTokensLeftException("To many tokens left");
        //here we return a new list of Tokens while also updating the list in the repository.
        return repository.updateTokenList(userID, generateNewSet(temp.getTokens().size()));
    }

    //method to consume a token so i can't be reused.
    public String consumeToken(String token) throws InvalidTokenException{
        try {
            return repository.consumeToken(token);
        }catch (UserNotFoundException e) {
            throw new InvalidTokenException("Invalid token, no such token");
        }
    }

    public TokenList getTokensFromUserID(String userID) throws UserNotFoundException {
        // If the userID is not contained in the register throw an exception
        return repository.getTokensByUser(userID);
    }

    //This method handles the responsibility of
    private TokenList generateNewSet(int tokensLeftOffSet) {
        TokenList list = new TokenList();
        String token;
        //the offset is used to make sure if the customer has 1 token left
        // so that we don't give him 6 new ones to a total of 7
        for (int i = 0; i < 6 - tokensLeftOffSet; i++) {
            String temp;
            do {
                token = generateRandomTokenString();
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
    private static String generateRandomTokenString() {
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
