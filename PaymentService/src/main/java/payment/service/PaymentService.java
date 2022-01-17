package payment.service;

import dtu.ws.fastmoney.BankService;
import dtu.ws.fastmoney.BankServiceException_Exception;
import dtu.ws.fastmoney.BankServiceService;
import payment.service.DTO.Payment;
import payment.service.port.IPaymentService;

public class PaymentService implements IPaymentService {
    BankService bankService = new BankServiceService().getBankServicePort();


    @Override
    public void transferMoney(Payment p) throws BankServiceException_Exception {
        bankService.transferMoneyFromTo(p.getDebitor(),p.getCreditor(),p.getAmount(),p.getDescription());
    }

    @Override
    public void refundMoney(Payment p) throws BankServiceException_Exception {
        bankService.transferMoneyFromTo(p.getCreditor(),p.getDebitor(),p.getAmount(),p.getDescription());
    }
}
