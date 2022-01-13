package token.service;

import token.service.DTO.TokenList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class TokenRegister {
    HashMap<String, String> register;

    public TokenRegister() {
        register = new HashMap<>();
    }
    //This Method handles the creation and checking of Tokens and the rules behind them as such
    public TokenList requestNewSet(String userID) throws ToManyTokensLeftException {
        //This method is used to make sure the user doesn't have more than the 1 allowed token left before requesting a new one
        int tokensLeft = verifyLessOrEqualThanOne(userID);
        //here we return a new list of Tokens
        return generateNewSet(userID, tokensLeft);
    }

    //This method handles the responsibility of 
    private TokenList generateNewSet(String userID, int tokensLeftOffSet) {
        TokenList list = new TokenList();
        String token;
        for (int i = 0; i < 6 - tokensLeftOffSet; i++) {
            do { token = generateRandomToken(); }
            while (register.containsValue(token));
            register.put(token, userID);
            list.getTokens().add(token);
        }
        return list;
    }

    private int verifyLessOrEqualThanOne(String userID) throws ToManyTokensLeftException {
        int count = 0;
        for(String key : register.keySet()) {            // iterate through all the keys in this HashMap
            if(register.get(key).equals(userID)) {  // if a key maps to the string you need, increment the counter
                count++;
            }
        }
        if (count <= 1)
            return count;
        else
            throw new ToManyTokensLeftException("you still have " + count + " tokens left");
    }
    
    public String consumeToken(String token) throws InvalidTokenException {
        String userID;
        try {
            //First we retrieve the userID from the token
            userID = getUserIDFromToken(token);
            //After retrieving the userID we can confirm the validation of that token and the remove it from the list
            //In order to prevent the token from being used multiple times
            register.remove(token);
            //Returns the userID
            return userID;
        }catch (NullPointerException e) {
            throw new InvalidTokenException("Invalid token, no such token");
        }
    }

    public boolean verifyValidityOfToken(String token) throws InvalidTokenException {
        // If token is valid and not used return true;
        if(register.get(token) == null)
            throw new InvalidTokenException("Invalid token, token is null") ;

        return true;
    }

    private String getUserIDFromToken(String token) throws InvalidTokenException {
        // If the token does not exist in the regiser throw an exception
        if (register.get(token) == null)
            throw new InvalidTokenException("Invalid token, no user with such token");
        return register.get(token);
    }

    // Inefficent since it has to run through whole hashMap for each call
    public TokenList getTokensFromUserID(String userID) throws UserNotFoundException {
        // If the userID is not contained in the register throw an exception
        if(!register.containsValue(userID))
            throw new UserNotFoundException("UserID does not exist or haven't requested any Tokens yet");
            
        // Run through the entire hashmap for the userID and add all keys for in the temp array    
        ArrayList<String> temp = new ArrayList<>();
        for(String key : register.keySet()) {
            if(register.get(key).equals(userID))
                temp.add(key);
        }
        // Make a new tokenList and set the tokenlist to temp and return.
        TokenList result = new TokenList();
        result.setTokens(temp);
        return result;
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
