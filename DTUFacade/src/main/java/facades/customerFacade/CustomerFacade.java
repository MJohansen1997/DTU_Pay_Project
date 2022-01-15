package facades.customerFacade;

import facades.DTO.RegistrationDTO;
import facades.exceptions.InvalidRegistrationInputException;
import messaging.Event;
import messaging.MessageQueue;

import java.util.concurrent.CompletableFuture;

public class CustomerFacade {
    private MessageQueue queue;
    private CompletableFuture<String> future;
    public CustomerFacade(MessageQueue q) {
        queue = q;
        queue.addHandler("CustomerRegisteredSuccessfully", this::successfulCustomerRegistration);
        queue.addHandler("CustomerBankIdNotFound", this::unsuccessfulCustomerRegistration);
        queue.addHandler("CustomerInvalidInput", this::unsuccessfulCustomerRegistration);
    }

    public String registerCustomer(RegistrationDTO regInfo) {
        future = new CompletableFuture<>();
        Event tempE = new Event("CustomerRegister",  new Object[] { regInfo });
            queue.publish(tempE);
        return future.join();
    }

    private void successfulCustomerRegistration(Event e) {
        var id = e.getArgument(0, String.class);
        future.complete(id);
    }
    private void unsuccessfulCustomerRegistration(Event e) {
        future.completeExceptionally(new InvalidRegistrationInputException(e.getType()));
    }
}
