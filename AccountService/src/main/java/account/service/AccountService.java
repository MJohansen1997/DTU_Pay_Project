package account.service;

import account.service.DTO.Account;
import account.service.port.IAccountService;
import dtu.ws.fastmoney.AccountInfo;
import dtu.ws.fastmoney.BankService;
import dtu.ws.fastmoney.BankServiceException_Exception;
import dtu.ws.fastmoney.BankServiceService;

import java.util.ArrayList;

public class AccountService implements IAccountService {
    BankService bankService = new BankServiceService().getBankServicePort();

    @Override
    public dtu.ws.fastmoney.Account merchantRegister(Account account) throws BankServiceException_Exception {
        return bankService.getAccount(account.getBankID());
    }

    @Override
    public dtu.ws.fastmoney.Account customerRegister(Account account) throws BankServiceException_Exception {
        return bankService.getAccount(account.getBankID());
    }

    @Override
    public void getMerchantList() {

    }

    @Override
    public void getCustomerList() {

    }

    @Override
    public ArrayList<dtu.ws.fastmoney.AccountInfo> getAccounts() {
        return (ArrayList<AccountInfo>) bankService.getAccounts();
    }
}
