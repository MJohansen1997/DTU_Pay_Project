package account.service;

import account.service.DTO.Account;
import account.service.repository.AccountRepository;
import account.service.repository.IAccountRepository;
import dtu.ws.fastmoney.BankService;
import dtu.ws.fastmoney.BankServiceException_Exception;
import dtu.ws.fastmoney.BankServiceService;

import java.util.InvalidPropertiesFormatException;

public class AccountService {
    BankService bankService = new BankServiceService().getBankServicePort();
    AccountRepository repo = new AccountRepository();
    IAccountService service;

    public AccountService(IAccountService service) {
        this.service = service;
    }

    public void registerUser(Account acc) {
        try {
            validateBankDetails(acc);
            validateRegistrationInput(acc);
            service.registerUser();

        } catch (InvalidPropertiesFormatException | BankServiceException_Exception e) {
            e.printStackTrace();
        }
    }

    private void validateBankDetails(Account accToValidate) throws BankServiceException_Exception, InvalidPropertiesFormatException {
        System.out.println("checking if bankid: " + accToValidate.getBankID() + " exists");
        bankService.getAccount(accToValidate.getBankID());
        System.out.println("checking if a user with the bankid already exists..");
        if(repo.getBankIds().contains(accToValidate.getBankID())) {
            throw new InvalidPropertiesFormatException("User with bank id: " + accToValidate.getBankID() + " already exists!");
        };
    }
    private void validateRegistrationInput(Account accToValidate) throws InvalidPropertiesFormatException {
        accToValidate.setCpr(accToValidate.getCpr().replace("-", ""));
        if (repo.getMerchantList().containsKey(accToValidate.getBankID()) || repo.getCustomerList().containsKey(accToValidate.getBankID()))
            throw new InvalidPropertiesFormatException("A user with the bank id provided already exists");
        if(accToValidate.getCpr().isEmpty() || accToValidate.getFirstName().isEmpty() || accToValidate.getLastName().isEmpty())
            throw new InvalidPropertiesFormatException("Empty fields not allowed");
        if(accToValidate.getCpr().length() != 10) {
            throw new InvalidPropertiesFormatException("CPR length not accepted!");
        }
    }
}
