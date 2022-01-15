package facades.customerFacade;

import facades.DTO.RegistrationDTO;
import facades.DTO.TokenList;
import messaging.Event;
import messaging.MessageQueue;

import javax.ws.rs.NotFoundException;
import java.util.concurrent.CompletableFuture;

public class CustomerFacade {
    private MessageQueue queue;
    private CompletableFuture<String> future;
    private CompletableFuture<TokenList> tokensRequested;

    public CustomerFacade(MessageQueue q) {
        queue = q;
        queue.addHandler("CustomerRegisteredSuccessfully", this::successfulCustomerRegistration);
        queue.addHandler("TokensRequestedSucceeded", this::successfulTokensRequest);
        queue.addHandler("TokensRequestedFailed", this::failedTokensRequest);
    }

    public String registerCustomer(RegistrationDTO regInfo) {
        future = new CompletableFuture<>();
        Event tempE = new Event("CustomerRegister", new Object[]{regInfo});
        queue.publish(tempE);
        return future.join();
    }

    public TokenList requestTokens(String userID) {
        tokensRequested = new CompletableFuture<>();
        Event event = new Event("TokensRequested", new Object[]{userID});
        queue.publish(event);
        return tokensRequested.join();
    }

    public void successfulTokensRequest(Event e) {
        var tokens = e.getArgument(0, TokenList.class);
        tokensRequested.complete(tokens);
    }

    public void failedTokensRequest(Event e) {
        var tokens = e.getArgument(0, String.class);
        tokensRequested.complete(null);
    }

    private void successfulCustomerRegistration(Event e) {
        var id = e.getArgument(0, String.class);
        future.complete(id);
    }
}
