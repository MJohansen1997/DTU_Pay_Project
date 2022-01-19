package facades.merchantFacade;

import facades.DTO.*;
import facades.enums.UserType;
import facades.exceptions.RegistrationException;
import facades.managerFacade.ReportList;
import messaging.Event;
import messaging.MessageQueue;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class MerchantFacade {
    private MessageQueue queue;
    private CompletableFuture<String> future;
    private CompletableFuture<Payment> paymentFuture;
    private CompletableFuture<String> tokeConsumerFuture;
    private CompletableFuture<Account> getSpecificUserFuture;
    private CompletableFuture<Report> reportFuture;
    private CompletableFuture<ReportList> reportRequested;
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
        queue.addHandler("TokenNotFound", this::handleTokenNotFound);
        queue.addHandler("FoundSpecificUser", this::handleFoundSpecificUser);
        queue.addHandler("FoundSpecificUser", this::handleFoundSpecificUser);
        queue.addHandler("ReportCreationRequestSucceeded",this::handleReportCreated);
        queue.addHandler("ReportCreationRequestFailed",this::handleReportCreated);
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

    public ReportList reportListRecived(String paymentID) {
        reportRequested = new CompletableFuture<>();
        Event event = new Event("ReportMerchant", new Object[] { paymentID });
        queue.publish(event);
        return reportRequested.join();
    }

    private void handleTokenNotFound(Event event) {
        var id = event.getArgument(0,String.class);
        tokeConsumerFuture.complete(id);
    }

    public Account getSpecificUser(String userID) {
        getSpecificUserFuture = new CompletableFuture<>();
        Event tempE = new Event("GetSpecificUserById",  new Object[] { userID });
        queue.publish(tempE);
        return getSpecificUserFuture.join();
    }

    private void handleFoundSpecificUser(Event e) {
        var id = e.getArgument(0, Account.class);
        getSpecificUserFuture.complete(id);
    }

    public Report createReport(ReportRequest reportRequest){
        reportFuture = new CompletableFuture<>();
        Event tempE = new Event("ReportCreationRequest",  new Object[] { reportRequest });
        queue.publish(tempE);
        reportFuture.orTimeout(10, TimeUnit.SECONDS);
        reportFuture.cancel(true);
        return reportFuture.join();
    }

    private void handleReportCreated(Event e) {
        var id = e.getArgument(0, Report.class);
        reportFuture.complete(id);
    }


}
