package facades.merchantFacade;

import facades.DTO.Payment;
import facades.DTO.RegistrationDTO;
import messaging.Event;
import messaging.MessageQueue;

import java.util.concurrent.CompletableFuture;

public class MerchantFacade {
    private MessageQueue queue;
    private CompletableFuture<String> future;
    private CompletableFuture<Payment> paymentFuture;
    private CompletableFuture<String> tokeConsumerFuture;
    
    public MerchantFacade(MessageQueue q) {
        queue = q;
        queue.addHandler("MerchantRegisteredSuccessfully", this::successfulMerchantRegistration);
        queue.addHandler("MerchantPaymentSuccessfully", this::successfulMerchantPayment);
        queue.addHandler("UserID fecthed", this::handleUserIdFetched);
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

    public Payment paymentMerchant(Payment payment) {
        paymentFuture = new CompletableFuture<>();
        Event tempE = new Event("MerchantPaymentRegistered",  new Object[] { payment });
        queue.publish(tempE);
        return paymentFuture.join();
    }

    public void successfulMerchantPayment(Event e) {
        var id = e.getArgument(0, String.class);
        future.complete(id);
    }

    public String consumeToken(String tokenID){
        tokeConsumerFuture = new CompletableFuture<>();
        Event tempE = new Event("ConsumeTokenRequested",  new Object[] { tokenID });
        queue.publish(tempE);
        return tokeConsumerFuture.join();
    }

    private void handleUserIdFetched(Event event) {
        var id = event.getArgument(0,String.class);
        tokeConsumerFuture.complete(id);
    }
}
