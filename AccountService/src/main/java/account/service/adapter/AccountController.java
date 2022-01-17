package account.service.adapter;

import account.service.AccountService;
import account.service.DTO.Account;
import account.service.exception.BankIdAlreadyRegisteredException;
import account.service.exception.InvalidRegistrationInputException;
import account.service.idGenerator;
import account.service.repository.AccountRepositoryAdapter;
import dtu.ws.fastmoney.AccountInfo;
import dtu.ws.fastmoney.BankServiceException_Exception;
import messaging.Event;
import messaging.MessageQueue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.concurrent.CompletableFuture;

public class AccountController
{
    MessageQueue queue;
    CompletableFuture<String> testFuture = new CompletableFuture<>();
        HashMap<String, Account> merchantList = new HashMap<>();
        HashMap<String, Account> customerList = new HashMap<>();
        HashMap<String, String> testList = new HashMap<>();

        AccountService accountService = new AccountService(new AccountRepositoryAdapter());

public AccountController(MessageQueue q) {
        queue = q;
//        queue.addHandler("MerchantRegister", this::merchantRegister);
        queue.addHandler("MerchantRegister", this::merchantRegister);
        queue.addHandler("CustomerRegister", this::customerRegister);
        queue.addHandler("GetMerchantList", this::getMerchantList);
        queue.addHandler("GetCustomerList", this::getCustomerList);
        queue.addHandler("GetSpecificMerchant", this::customerRegister);
        queue.addHandler("GetSpecificCustomer", this::customerRegister);
        queue.addHandler("MerchantGetAccounts", this::handleMerchantGetAccounts);
        }




public void merchantRegister(Event event) {
        try {
        Account acc = event.getArgument(0, Account.class);
        accountService.registerUser(acc, "m");

//        System.out.println("Printing events converted to acc: " + acc.toString());

        /* Generating unique user id */
        String id = idGenerator.generateID("m");
        acc.setRoleID(id);
        /* Saves the user */
        customerList.put(id, acc);
        /* Publishes event that the register happened successfully */
        Event tempEvent = new Event("MerchantRegisteredSuccessfully", new Object[] {id});
        queue.publish(tempEvent);

        } catch (BankServiceException_Exception e) {
        System.out.println("Bank id not found");
        Event tempEvent = new Event("MerchantBankIdNotFound", new Object[] {});
        queue.publish(tempEvent);
        } catch (InvalidPropertiesFormatException e) {
        System.out.println("Wrong registration input");
        Event tempEvent = new Event("MerchantInvalidInput", new Object[] {});
        queue.publish(tempEvent);
        } catch (BankIdAlreadyRegisteredException | InvalidRegistrationInputException e) {
                e.printStackTrace();
        }

}

public void customerRegister(Event event) {
        try {
        Account acc = event.getArgument(0, Account.class);
        System.out.println("checking if bankid: " +acc.getBankID() + " exists");
        accountService.customerRegister(acc);
        System.out.println("Bank id found! Creating user");
//        System.out.println("Printing events converted to acc: " + acc.toString());
        /* Generating unique user id */
        validateRegistrationInput(acc);
        String id = idGenerator.generateID("c");
        acc.setRoleID(id);
        /* Saves the user */
        customerList.put(id, acc);
        /* Publishes event that the register happened successfully */
        Event tempEvent = new Event("CustomerRegisteredSuccessfully", new Object[] {id});
        queue.publish(tempEvent);

        } catch (BankServiceException_Exception e) {
        System.out.println("Bank id not found");
        Event tempEvent = new Event("CustomerBankIdNotFound", new Object[] {});
        queue.publish(tempEvent);

        } catch (InvalidPropertiesFormatException e) {
        System.out.println("Wrong registration input");
        Event tempEvent = new Event("CustomerInvalidInput", new Object[] {});
        queue.publish(tempEvent);
        }
        }

private void validateRegistrationInput(Account accToValidate) throws InvalidPropertiesFormatException {
        accToValidate.setCpr(accToValidate.getCpr().replace("-", ""));
        if(accToValidate.getCpr().isEmpty() || accToValidate.getFirstName().isEmpty() || accToValidate.getLastName().isEmpty())
        throw new InvalidPropertiesFormatException("Empty fields not allowed");
        if(accToValidate.getCpr().length() != 10) {
        throw new InvalidPropertiesFormatException("CPR length not accepted!");
        }
        }

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

public void handleMerchantGetAccounts(Event event) {
        ArrayList<AccountInfo> accounts = (ArrayList<AccountInfo>) accountService.getAccounts();
        event = new Event("MerchantGetAccountsSuccessful", new Object[] { accounts });
        queue.publish(event);
        }
}