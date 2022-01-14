package facades.merchantFacade;

import facades.DTO.RegistrationDTO;
import messaging.Event;
import messaging.MessageQueue;

import java.util.concurrent.CompletableFuture;

public class MerchantFacade {
    private MessageQueue queue;
    private CompletableFuture<String> future;
    
    public MerchantFacade(MessageQueue q) {
        queue = q;
        queue.addHandler("MerchantRegisteredSuccessfully", this::successfulMerchantRegistration);
    }

    public String registerMerchant(RegistrationDTO regInfo) {
        future = new CompletableFuture<>();
        Event tempE = new Event("CustomerMerchant",  new Object[] { regInfo });
        queue.publish(tempE);
        return future.join();
    }
    public void successfulMerchantRegistration(Event e) {
        var id = e.getArgument(0, String.class);
        future.complete(id);
    }
}
