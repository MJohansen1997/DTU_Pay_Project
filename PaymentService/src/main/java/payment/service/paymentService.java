package payment.service;

import dtu.ws.fastmoney.BankService;
import dtu.ws.fastmoney.BankServiceService;
import messaging.Event;
import messaging.MessageQueue;
import payment.service.DTO.Payment;

public class paymentService {
    MessageQueue queue;
    BankService bankService = new BankServiceService().getBankServicePort();
    public paymentService(MessageQueue q) {
        this.queue = q;
        this.queue.addHandler("MerchantPaymentRequested", this::handlePaymentRequested);
    }

    public void handlePaymentRequested(Event ev) {
        var p = ev.getArgument(0, Payment.class);
        Event event;
        //Do BusinessLogic
        try {
            bankService.transferMoneyFromTo(p.getDebitor(),p.getCreditor(),p.getAmount(),p.getDescription());
            event = new Event("MerchantPaymentSuccessfully", new Object[] { p });
        } catch (/*BankServiceException_Exception*/Exception e) {
            event = new Event("MerchantPaymentSuccessfully", new Object[] { p });
        }
        queue.publish(event);
    }
}
