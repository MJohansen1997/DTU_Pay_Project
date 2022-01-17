package token.service.adapter;

import messaging.Event;
import messaging.MessageQueue;
import token.service.TokenService;
import token.service.exceptions.InvalidTokenException;
import token.service.exceptions.ToManyTokensLeftException;
import token.service.exceptions.UserNotFoundException;

public class TokenController {

    MessageQueue queue;
    private final TokenService tokenService = new TokenService();

    public TokenController(MessageQueue q) {
        queue = q;
        queue.addHandler("CustomerRegisteredSuccessfully", this::handleCreationRequest);
        queue.addHandler("TokensRequested", this::handleTokensRequested);
        queue.addHandler("ConsumeTokenRequested", this::handleConsumeTokenRequested);
        queue.addHandler("TokensFromUserIDRequested", this::handleTokensFromUserID);
    }

    public void handleCreationRequest(Event event) {
        var userID = event.getArgument(0, String.class);
        tokenService.createUser(userID);
    }

    public void handleTokensRequested(Event event) {
        System.out.println("Message received");
        var userID = event.getArgument(0, String.class);
        Event returnEvent;
        try {
            returnEvent = new Event("TokensRequestedSucceeded", new Object[] {tokenService.requestNewSet(userID)});
            queue.publish(returnEvent);
        } catch (ToManyTokensLeftException e) {
            returnEvent = new Event("ToManyTokensLeft", new Object[] {});
            queue.publish(returnEvent);
        } catch (UserNotFoundException e) {
            returnEvent = new Event("TokenUserNotFound", new Object[] {});
            queue.publish(returnEvent);
        }
    }

    public void handleConsumeTokenRequested(Event event) {
        var s = event.getArgument(0, String.class);
        try {
            Event returnEvent = new Event("UserID fecthed", new Object[] {tokenService.consumeToken(s)});
            queue.publish(returnEvent);
        } catch (InvalidTokenException e){
            Event returnEvent = new Event("UserID fecthed", new Object[] {e.getMessage()});
            queue.publish(returnEvent);
        }
    }

    public void handleTokensFromUserID(Event event) {
        var s = event.getArgument(0, String.class);
        try {
            Event returnEvent = new Event("Tokens fetched", new Object[] {tokenService.getTokensFromUserID(s)});
            queue.publish(returnEvent);
        } catch(UserNotFoundException e) {
            Event returnEvent = new Event("Tokens fetched", new Object[] {e.getMessage()});
            queue.publish(returnEvent);
        }
    }

}
