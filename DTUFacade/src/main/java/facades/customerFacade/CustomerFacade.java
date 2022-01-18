package facades.customerFacade;

import facades.DTO.RegistrationDTO;
import facades.DTO.TokenList;
import facades.enums.UserType;
import facades.exceptions.RegistrationException;
import facades.exceptions.ToManyTokensLeftException;
import facades.managerFacade.ReportList;
import messaging.Event;
import messaging.MessageQueue;

import java.util.concurrent.CompletableFuture;

public class CustomerFacade {
    private MessageQueue queue;
    private CompletableFuture<String> future;
    private CompletableFuture<TokenList> tokensRequested;
    private CompletableFuture<ReportList> reportRequested;

    public CustomerFacade(MessageQueue q) {
        queue = q;
        queue.addHandler("CustomerRegisteredSuccessfully", this::successfulCustomerRegistration);
        queue.addHandler("TokensRequestedSucceeded", this::successfulTokensRequest);
        queue.addHandler("ToManyTokensLeft", this::failedTokensRequest);
        queue.addHandler("TokenUserNotFound", this::failedTokensRequest);
        queue.addHandler("CustomerBankIdNotFound", this::unsuccessfulCustomerRegistration);
        queue.addHandler("CustomerInvalidInput", this::unsuccessfulCustomerRegistration);
        queue.addHandler("CustomerUnsuccessfulRegistration", this::unsuccessfulCustomerRegistration);

    }

    public String registerCustomer(RegistrationDTO regInfo) {
        future = new CompletableFuture<>();
        Event tempE = new Event("UserRegister", new Object[]{regInfo, UserType.CUSTOMER});
        queue.publish(tempE);
        return future.join();
    }

    //Used to request tokens from the Token microservice
    public TokenList requestTokens(String userID) {
        tokensRequested = new CompletableFuture<>();
        Event event = new Event("TokensRequested", new Object[]{userID});
        queue.publish(event);
        return tokensRequested.join();
    }

    //Handles if the TokenList gets send successfully
    public void successfulTokensRequest(Event e) {
        var tokens = e.getArgument(0, TokenList.class);
        tokensRequested.complete(tokens);
    }

    //Handles if the TokenList does not get send successfully
    public void failedTokensRequest(Event e) {
        tokensRequested.completeExceptionally(new ToManyTokensLeftException(e.getType()));
    }


    private void successfulCustomerRegistration(Event e) {
        var id = e.getArgument(0, String.class);
        future.complete(id);
    }
    private void unsuccessfulCustomerRegistration(Event e) {
        future.completeExceptionally(new RegistrationException(e.getArgument(0, String.class)));
    }

    public ReportList reportListRecived(String paymentID) {
        reportRequested = new CompletableFuture<>();
        Event event = new Event("ReportCustomer", new Object[] { paymentID });
        queue.publish(event);
        return reportRequested.join();
    }
}
