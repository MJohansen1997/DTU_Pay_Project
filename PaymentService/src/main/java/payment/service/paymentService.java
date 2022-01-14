package payment.service;

import messaging.Event;
import messaging.MessageQueue;
import payment.service.DTO.Payment;

public class paymentService {
    MessageQueue queue;
    //Facade facade = new Facade();
    public paymentService(MessageQueue q) {
        this.queue = q;
        this.queue.addHandler("PaymentRequested", this::handlePaymentRequested);
    }

    public void handlePaymentRequested(Event ev) {
        var p = ev.getArgument(0, Payment.class);
        Event event;
        //Do BusinessLogic
        try {
            //facade.transferMoneyFromTo(p.getDebitor(),p.getCreditor(),p.getAmount(),p.getDescription());
            event = new Event("PaymentCompleted", new Object[] { p });
        } catch (/*BankServiceException_Exception*/Exception e) {
            event = new Event("PaymentCompleted", new Object[] { p });
        }
        queue.publish(event);
    }
}
