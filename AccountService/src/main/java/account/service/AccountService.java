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

//Engages with our AccountAdapter
public class AccountService {
    BankService bankService = new BankServiceService().getBankServicePort();
    IExternalService service;

    //Default constructor
    public AccountService(IExternalService service) {
        this.service = service;
    }

    //Registers a user with a role of either merchant or customer and stores the user in our AccountRepoistory
    public String registerUser(Account acc, String role) throws BankIdAlreadyRegisteredException, InvalidRegistrationInputException, BankIdNotFoundException {
        try {
            validateBankDetails(acc);
            validateRegistrationInput(acc);
            String id = idGenerator.generateID(role);
            service.registerUser(acc, id);
            return id;

        } catch (BankServiceException_Exception e) {
            throw new BankIdNotFoundException("BankIdNotFoundException:"+ "Bank id " + acc.getBankID() + " Does not exist.");
        }
    }

    //Will get a specific user based of their id
    public Account getSpecifiedUser(String userId) throws UserNotFoundException {
        return service.getSpecificUser(userId);
    }

    //Helper function for registering a user. Which validates their bankID
    private void validateBankDetails(Account accToValidate) throws BankServiceException_Exception {
        System.out.println("checking if bankid: " + accToValidate.getBankID() + " exists");
        bankService.getAccount(accToValidate.getBankID());
    }

    //Validates that all input is legal
    private void validateRegistrationInput(Account accToValidate) throws InvalidRegistrationInputException {
        accToValidate.setCpr(accToValidate.getCpr().replace("-", ""));

        if(accToValidate.getCpr().isEmpty() || accToValidate.getFirstName().isEmpty() || accToValidate.getLastName().isEmpty())
            throw new InvalidRegistrationInputException("InvalidInputEmptyFields");
        if(accToValidate.getCpr().length() != 10) {
            throw new InvalidRegistrationInputException("InvalidInputCprIncorrect");
        }
    }
}
