package account.service.adapter;

import account.service.AccountService;
import account.service.DTO.Account;
import account.service.exception.BankIdAlreadyRegisteredException;
import account.service.exception.InvalidRegistrationInputException;
import account.service.exception.UserNotFoundException;
import account.service.repository.AccountRepositoryAdapter;
import dtu.ws.fastmoney.BankService;
import dtu.ws.fastmoney.BankServiceService;
import messaging.Event;
import messaging.MessageQueue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.CompletableFuture;

public class FacadeAdapter {
    BankService bankService = new BankServiceService().getBankServicePort();
    MessageQueue queue;
    CompletableFuture<String> testFuture = new CompletableFuture<>();
    HashMap<String, Account> merchantList = new HashMap<>();
    HashMap<String, Account> customerList = new HashMap<>();
    HashSet<String> bankIds = new HashSet<>();
    HashMap<String, String> testList = new HashMap<>();

    AccountService accountService = new AccountService(new AccountRepositoryAdapter());


    public FacadeAdapter(MessageQueue q) {
        queue = q;
//        queue.addHandler("MerchantRegister", this::merchantRegister);
//        queue.addHandler("MerchantRegister", this::merchantRegister);
//        queue.addHandler("CustomerRegister", this::customerRegister);
//        queue.addHandler("GetMerchantList", this::getMerchantList);
//        queue.addHandler("GetCustomerList", this::getCustomerList);
//        queue.addHandler("GetSpecificMerchant", this::getSpecificUser);
//        queue.addHandler("GetSpecificCustomer", this::customerRegister);

        queue.addHandler("UserRegister", this::registerUser);
        queue.addHandler("GetSpecificUserById", this::getSpecificUser);

    }

    public void registerUser(Event event) {
        Account acc = event.getArgument(0, Account.class);
        String role = event.getArgument(1, String.class);
        try {
            accountService.registerUser(acc, role);
            Event temp = new Event(role+"RegisteredSuccessfully");
            queue.publish(temp);
        } catch (BankIdAlreadyRegisteredException | InvalidRegistrationInputException e) {
            Event temp = new Event(role+e.getMessage());
            queue.publish(temp);
        }
    }

    public void getSpecificUser(Event event) {
        String id = event.getArgument(0,  String.class);
        try {
            Account acc = accountService.getSpecifiedUser(id);
            Event temp = new Event("FoundSpecificUser", new Object[] {acc});
            queue.publish(temp);
        } catch (UserNotFoundException e) {
            Event temp = new Event(e.getMessage(), new Object[] {});
            queue.publish(temp);
        }
    }

//    public void merchantRegister(Event event) {
//        try {
//            Account acc = event.getArgument(0, Account.class);
//            accountService.registerUser(acc, "m");
//            Account acc = event.getArgument(0, Account.class);
//            System.out.println("checking if bankid: " +acc.getBankID() + " exists");
//            bankService.getAccount(acc.getBankID());
//            System.out.println("Bank id found! Creating user");
////        System.out.println("Printing events converted to acc: " + acc.toString());
//            validateRegistrationInput(acc);
//
//            /* Generating unique user id */
//            String id = idGenerator.generateID("m");
//            acc.setRoleID(id);
//            /* Saves the user */
//            customerList.put(id, acc);
//            bankIds.add(acc.getBankID());
//            /* Publishes event that the register happened successfully */
//            Event tempEvent = new Event("MerchantRegisteredSuccessfully", new Object[] {id});
//            queue.publish(tempEvent);
//
//        } catch (BankServiceException_Exception e) {
//            System.out.println("Bank id not found");
//            Event tempEvent = new Event("MerchantBankIdNotFound", new Object[] {});
//            queue.publish(tempEvent);
//        } catch (InvalidPropertiesFormatException e) {
//            System.out.println("Wrong registration input");
//            Event tempEvent = new Event("MerchantInvalidInput", new Object[] {});
//            queue.publish(tempEvent);
//        }
//
//    }
//
//    public void customerRegister(Event event) {
//        try {
//            Account acc = event.getArgument(0, Account.class);
////        System.out.println("Printing events converted to acc: " + acc.toString());
//            /* Validating input details */
//            validateBankDetails(acc);
//            validateRegistrationInput(acc);
//
//            /* Generating unique user id */
//            String id = idGenerator.generateID("c");
//            acc.setRoleID(id);
//
//            /* Saves the user */
//            customerList.put(id, acc);
//            bankIds.add(acc.getBankID());
//            /* Publishes event that the register happened successfully */
//            Event tempEvent = new Event("CustomerRegisteredSuccessfully", new Object[] {id});
//            queue.publish(tempEvent);
//
//        } catch (BankServiceException_Exception e) {
//            System.out.println("Bank id not found");
//            Event tempEvent = new Event("CustomerBankIdNotFound", new Object[] {});
//            queue.publish(tempEvent);
//
//        } catch (InvalidPropertiesFormatException e) {
//            System.out.println("Wrong registration input");
//            Event tempEvent = new Event("CustomerInvalidInput", new Object[] {});
//            queue.publish(tempEvent);
//        }
//    }
//
//    private void validateBankDetails(Account accToValidate) throws BankServiceException_Exception, InvalidPropertiesFormatException {
//        System.out.println("checking if bankid: " + accToValidate.getBankID() + " exists");
//        bankService.getAccount(accToValidate.getBankID());
//        System.out.println("checking if a user with the bankid already exists..");
//        if(bankIds.contains(accToValidate.getBankID())) {
//            throw new InvalidPropertiesFormatException("User with bank id: " + accToValidate.getBankID() + " already exists!");
//        };
//    }
//    private void validateRegistrationInput(Account accToValidate) throws InvalidPropertiesFormatException {
//        accToValidate.setCpr(accToValidate.getCpr().replace("-", ""));
//        if (merchantList.containsKey(accToValidate.getBankID()) || customerList.containsKey(accToValidate.getBankID()))
//            throw new InvalidPropertiesFormatException("A user with the bank id provided already exists");
//        if(accToValidate.getCpr().isEmpty() || accToValidate.getFirstName().isEmpty() || accToValidate.getLastName().isEmpty())
//            throw new InvalidPropertiesFormatException("Empty fields not allowed");
//        if(accToValidate.getCpr().length() != 10) {
//            throw new InvalidPropertiesFormatException("CPR length not accepted!");
//        }
//    }

    public void getCustomerList(Event event) {
        Event custListEvent = new Event("ReturningCustomerList", new Object[] {customerList.values()});
        queue.publish(custListEvent);
    }
    public void getMerchantList(Event event) {
        Event mercListEvent = new Event("ReturningMerchantList", new Object[] {merchantList});
        queue.publish(mercListEvent);
    }

    public void getSpecificCustomer(Event event) {

    }
    public void getSpecificMerchant(Event event) {

    }
}
