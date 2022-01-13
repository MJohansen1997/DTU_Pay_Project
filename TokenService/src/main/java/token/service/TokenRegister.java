package token.service;

import java.util.HashMap;
import java.util.Random;

public class TokenRegister {
    HashMap<String, String> register;

    public TokenRegister() {
        register = new HashMap<>();
    }

    public void requestNewSet(String userID) {
        register.put(userID, generateRandomToken());
    }

    public TokenList generateNewSet(String userID) {
        for (int i = 0; i < register.size(); i++) {
        }
        return null;
    }

    //Taken from https://www.codegrepper.com/code-examples/java/java+generate+token+string
        public static String generateRandomToken() {
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
