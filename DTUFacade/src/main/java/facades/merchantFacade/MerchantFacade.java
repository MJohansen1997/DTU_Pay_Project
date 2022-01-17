package facades.merchantFacade;

import facades.DTO.Payment;
import facades.DTO.RegistrationDTO;
import facades.exceptions.InvalidRegistrationInputException;
import messaging.Event;
import messaging.MessageQueue;

import java.security.InvalidAlgorithmParameterException;
import java.util.concurrent.CompletableFuture;

public class MerchantFacade {
    private MessageQueue queue;
    private CompletableFuture<String> future;
    private CompletableFuture<Payment> paymentFuture;
    private CompletableFuture<String> tokeConsumerFuture;
    RegisterMerchant rm;
    private Payment p;

    public MerchantFacade(MessageQueue q) {
        queue = q;
//        rm = new RegisterMerchant();
        queue.addHandler("MerchantRegisteredSuccessfully", this::successfulMerchantRegistration);
        queue.addHandler("MerchantPaymentSuccessfully", this::successfulMerchantPayment);
        queue.addHandler("MerchantPaymentFailed", this::handleMerchantPaymentFailed);
        queue.addHandler("MerchantBankIdNotFound", this::unsuccessfulMerchantRegistration);
        queue.addHandler("MerchantInvalidInput", this::unsuccessfulMerchantRegistration);
        queue.addHandler("UserID fecthed", this::handleUserIdFetched);
    }


    public String registerMerchant(RegistrationDTO regInfo) throws InvalidRegistrationInputException {
        future = new CompletableFuture<>();
        Event tempE = new Event("MerchantRegister",  new Object[] { regInfo });
        queue.publish(tempE);
        return future.join();
    }
    public void successfulMerchantRegistration(Event e) {
        var id = e.getArgument(0, String.class);
        future.complete(id);
    }

    private void unsuccessfulMerchantRegistration(Event e) {
        future.completeExceptionally(new InvalidRegistrationInputException(e.getType()));
    }

    public Payment paymentMerchant(Payment payment) {
        paymentFuture = new CompletableFuture<>();
        Event tempE = new Event("MerchantPaymentRequested",  new Object[] { payment });
        queue.publish(tempE);
        return paymentFuture.join();
    }

    public void successfulMerchantPayment(Event e) {
        var id = e.getArgument(0, Payment.class);
        paymentFuture.complete(id);
    }

    private void handleMerchantPaymentFailed(Event e) {
        var id = e.getArgument(0, Payment.class);
        paymentFuture.complete(id);
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
