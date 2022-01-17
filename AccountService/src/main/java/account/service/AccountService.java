package account.service;

import account.service.DTO.Account;
import account.service.exception.BankIdAlreadyRegisteredException;
import account.service.exception.BankIdNotFoundException;
import account.service.exception.InvalidRegistrationInputException;
import account.service.exception.UserNotFoundException;
import account.service.port.IExternalService;
import dtu.ws.fastmoney.BankService;
import dtu.ws.fastmoney.BankServiceException_Exception;
import dtu.ws.fastmoney.BankServiceService;

public class AccountService {
    BankService bankService = new BankServiceService().getBankServicePort();
    IExternalService service;

    public AccountService(IExternalService service) {
        this.service = service;
    }

    public String registerUser(Account acc, String role) throws BankIdAlreadyRegisteredException, InvalidRegistrationInputException, BankIdNotFoundException {
        try {
            System.out.println("før validate");
            validateBankDetails(acc);
            System.out.println("efter bank validate");
            validateRegistrationInput(acc);
            System.out.println("efter input validate");
            String id = idGenerator.generateID(role);
            service.registerUser(acc, id);
            return id;

        } catch (BankServiceException_Exception e) {
            throw new BankIdNotFoundException("BankIdNotFoundException:"+ "Bank id " + acc.getBankID() + " Does not exist.");
        }
    }

    public Account getSpecifiedUser(String userId) throws UserNotFoundException {
        return service.getSpecificUser(userId);
    }

    private void validateBankDetails(Account accToValidate) throws BankServiceException_Exception {
        System.out.println("checking if bankid: " + accToValidate.getBankID() + " exists");
        bankService.getAccount(accToValidate.getBankID());
    }

    private void validateRegistrationInput(Account accToValidate) throws InvalidRegistrationInputException {
        accToValidate.setCpr(accToValidate.getCpr().replace("-", ""));

        if(accToValidate.getCpr().isEmpty() || accToValidate.getFirstName().isEmpty() || accToValidate.getLastName().isEmpty())
            throw new InvalidRegistrationInputException("InvalidInputEmptyFields");
        if(accToValidate.getCpr().length() != 10) {
            throw new InvalidRegistrationInputException("InvalidInputCprIncorrect");
        }
    }
}
