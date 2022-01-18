package Bank;

import dtu.ws.fastmoney.*;

import java.math.BigDecimal;

public class BankAccountManager {
    static BankService bank = new BankServiceService().getBankServicePort();

    public static String createAccount(String firstName, String lastName, String CPR, BigDecimal balance) throws BankServiceException_Exception {
        ObjectFactory objectFactory = new ObjectFactory();
        User user = objectFactory.createUser();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setCprNumber(CPR);
        return bank.createAccountWithBalance(user, balance);
    }

    public static void retireAccount(String bankID) throws BankServiceException_Exception {
        bank.retireAccount(bankID);
    }

    public static BigDecimal getBalance(String bankID) throws BankServiceException_Exception {
        return bank.getAccount(bankID).getBalance();
    }

    public static void transferMoney(String cID, String mID, BigDecimal amount, String description) throws BankServiceException_Exception {
        bank.transferMoneyFromTo(cID, mID, amount, description);
    }

    public static boolean getAccount(String bankID) throws BankServiceException_Exception {
        return bank.getAccount(bankID) != null;
    }
}
