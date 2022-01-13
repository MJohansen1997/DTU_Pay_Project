package token.service;

import messaging.Event;
import messaging.MessageQueue;

public class TokenService {

    MessageQueue queue;
    TokenRegister register = new TokenRegister();

    public  TokenService(MessageQueue q) {
        queue = q;
        queue.addHandler("TokensRequested", this::handleTokensRequested);
    }

    public void handleTokensRequested(Event event) {
        var s = event.getArgument(0, String.class);
    }

}
