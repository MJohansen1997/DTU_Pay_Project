package account.service.port;



import account.service.DTO.Account;
import dtu.ws.fastmoney.AccountInfo;
import dtu.ws.fastmoney.BankServiceException_Exception;

import java.util.ArrayList;

public interface IAccountService {

    public dtu.ws.fastmoney.Account merchantRegister(Account account) throws BankServiceException_Exception;
    public dtu.ws.fastmoney.Account customerRegister(Account account) throws BankServiceException_Exception;
    public void getMerchantList();
    public void getCustomerList();
    public ArrayList<AccountInfo> getAccounts();

}
