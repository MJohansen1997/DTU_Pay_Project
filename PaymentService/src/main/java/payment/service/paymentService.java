package payment.service;

import dtu.ws.fastmoney.BankService;
import dtu.ws.fastmoney.BankServiceException_Exception;
import dtu.ws.fastmoney.BankServiceService;
import messaging.Event;
import messaging.MessageQueue;
import payment.service.DTO.Payment;

import java.math.BigDecimal;

public class paymentService {
    MessageQueue queue;
    BankService bankService = new BankServiceService().getBankServicePort();
    public paymentService(MessageQueue q) {
        this.queue = q;
        this.queue.addHandler("MerchantPaymentRequested", this::handlePaymentRequested);
        this.queue.addHandler("MerchantRefundRequested", this::handleRefundRequested);
    }

    public void handlePaymentRequested(Event ev) {
        var p = ev.getArgument(0, Payment.class);
        Event event;
        //Do BusinessLogic
        try {
            bankService.transferMoneyFromTo(p.getDebitor(),p.getCreditor(),p.getAmount(),p.getDescription());
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
            bankService.transferMoneyFromTo(p.getCreditor(),p.getDebitor(),p.getAmount(),p.getDescription());
            event = new Event("MerchantRefundSuccessfully", new Object[] { p });
        } catch (BankServiceException_Exception e) {
            p = new Payment("","",new BigDecimal("0"),"",e.getMessage());
            event = new Event("MerchantRefundFailed", new Object[] { p });
        }
        queue.publish(event);
    }
}
