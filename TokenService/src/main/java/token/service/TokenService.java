package token.service;

import messaging.Event;
import messaging.MessageQueue;

public class TokenService {

    MessageQueue queue;
    TokenRegister register = new TokenRegister();

    public  TokenService(MessageQueue q) {
        queue = q;
        queue.addHandler("TokensRequested", this::handleTokensRequested);
        queue.addHandler("TokenVerificationRequested", this::handleTokenVerificationRequested);
        queue.addHandler("ConsumeTokenRequested", this::handleConsumeTokenRequested);
        queue.addHandler("TokensFromUserIDRequested", this::handleTokensFromUserID);
    }

    public void handleTokensRequested(Event event) {
        System.out.println("Message received");
        var s = event.getArgument(0, String.class);
        Event returnEvent;
        try {
            returnEvent = new Event("TokensRequestedSucceeded", new Object[] {register.requestNewSet(s)});
            queue.publish(returnEvent);
        } catch (ToManyTokensLeftException e) {
            returnEvent = new Event("TokensRequestedFailed", new Object[] {e.getMessage()});
            queue.publish(returnEvent);
        }
    }

    public void handleTokenVerificationRequested(Event event) {
        var s = event.getArgument(0, String.class);
        Event returnEvent;
        try {
            returnEvent = new Event("TokenVerificationRequestedSucceeded", new Object[] {register.verifyValidityOfToken(s)});
            queue.publish(returnEvent);
        } catch (InvalidTokenException e) {
            returnEvent = new Event("TokenVerificationRequestedFailed", new Object[] {e.getMessage()});
            queue.publish(returnEvent);
        }
    }

    public void handleConsumeTokenRequested(Event event) {
        var s = event.getArgument(0, String.class);
        try {
            Event returnEvent = new Event("UserID fecthed", new Object[] {register.consumeToken(s)});
            queue.publish(returnEvent);
        } catch (InvalidTokenException e){
            Event returnEvent = new Event("UserID fecthed", new Object[] {e.getMessage()});
            queue.publish(returnEvent);
        }
    }

    public void handleTokensFromUserID(Event event) {
        var s = event.getArgument(0, String.class);

        try {
            Event returnEvent = new Event("Tokens fetched", new Object[] {register.getTokensFromUserID(s)});
            queue.publish(returnEvent);
        } catch(UserNotFoundException e) {
            Event returnEvent = new Event("Tokens fetched", new Object[] {e.getMessage()});
            queue.publish(returnEvent);
        }
    }

}
