package facades.merchantFacade;

import facades.DTO.*;
import facades.enums.UserType;
import facades.exceptions.RegistrationException;
import messaging.Event;
import messaging.MessageQueue;

import java.util.concurrent.CompletableFuture;

public class MerchantFacade {
    private MessageQueue queue;
    private CompletableFuture<String> future;
    private CompletableFuture<Payment> paymentFuture;
    private CompletableFuture<String> tokeConsumerFuture;
    private CompletableFuture<Account> getSpecificUserFuture;
    private CompletableFuture<Report> reportFuture;
    private Payment p;

    public MerchantFacade(MessageQueue q) {
        queue = q;
//        rm = new RegisterMerchant();
        queue.addHandler("MerchantRegisteredSuccessfully", this::successfulMerchantRegistration);
        queue.addHandler("MerchantPaymentSuccessfully", this::successfulMerchantPayment);
        queue.addHandler("MerchantPaymentFailed", this::handleMerchantPaymentFailed);
//        queue.addHandler("MerchantBankIdNotFound", this::unsuccessfulMerchantRegistration);
//        queue.addHandler("MerchantInvalidInput", this::unsuccessfulMerchantRegistration);
        queue.addHandler("MerchantUnsuccessfulRegistration", this::unsuccessfulMerchantRegistration);
        queue.addHandler("UserID fecthed", this::handleUserIdFetched);
    }


    public String registerMerchant(RegistrationDTO regInfo) {
        future = new CompletableFuture<>();
        Event tempE = new Event("UserRegister",  new Object[] { regInfo, UserType.MERCHANT});
        queue.publish(tempE);
        return future.join();
    }
    public void successfulMerchantRegistration(Event e) {
        var id = e.getArgument(0, String.class);
        future.complete(id);
    }

    private void unsuccessfulMerchantRegistration(Event e) {
        future.completeExceptionally(new RegistrationException(e.getArgument(0,String.class)));
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

    public Account getSpecificUser(String userID) {
        getSpecificUserFuture = new CompletableFuture<>();
        Event tempE = new Event("GetSpecificUserById",  new Object[] { userID });
        queue.publish(tempE);
        return getSpecificUserFuture.join();
    }

    public Report createReport(ReportRequest reportRequest){
        reportFuture = new CompletableFuture<>();
        Event tempE = new Event("ReportCreationRequest",  new Object[] { reportRequest });
        queue.publish(tempE);
        return reportFuture.join();
    }
}
