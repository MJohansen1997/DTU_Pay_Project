package payment.service.adapter;

import dtu.ws.fastmoney.BankService;
import dtu.ws.fastmoney.BankServiceException_Exception;
import dtu.ws.fastmoney.BankServiceService;
import messaging.Event;
import messaging.MessageQueue;
import payment.service.DTO.Payment;
import payment.service.PaymentService;

import java.math.BigDecimal;

public class PaymentController {
    MessageQueue queue;
    PaymentService paymentService = new PaymentService();
    public PaymentController(MessageQueue q) {
        this.queue = q;
        this.queue.addHandler("MerchantPaymentRequested", this::handlePaymentRequested);
        this.queue.addHandler("MerchantRefundRequested", this::handleRefundRequested);
    }

    public void handlePaymentRequested(Event ev) {
        var p = ev.getArgument(0, Payment.class);
        Event event;
        //Do BusinessLogic
        try {
            paymentService.transferMoney(p);
            event = new Event("MerchantPaymentSuccessfully", new Object[] { p });
        } catch (BankServiceException_Exception e) {
            p = new Payment("","",new BigDecimal("0"),"",e.getMessage());
            event = new Event("MerchantPaymentFailed", new Object[] { p });
        }
        queue.publish(event);
    }

    public void handleRefundRequested(Event ev) {
        var p = ev.getArgument(0, Payment.class);
        Event event;
        //Do BusinessLogic
        try {
            paymentService.refundMoney(p);
            event = new Event("MerchantRefundSuccessfully", new Object[] { p });
        } catch (BankServiceException_Exception e) {
            p = new Payment("","",new BigDecimal("0"),"",e.getMessage());
            event = new Event("MerchantRefundFailed", new Object[] { p });
        }
        queue.publish(event);
    }
}
