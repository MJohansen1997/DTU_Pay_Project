package account.service;

import account.service.DTO.Account;
import dtu.ws.fastmoney.BankService;
import dtu.ws.fastmoney.BankServiceException_Exception;
import dtu.ws.fastmoney.BankServiceService;
import messaging.Event;
import messaging.MessageQueue;

import javax.ws.rs.NotFoundException;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

public class AccountService {
    BankService bankService = new BankServiceService().getBankServicePort();
    MessageQueue queue;
    CompletableFuture<String> testFuture = new CompletableFuture<>();
    HashMap<String, Account> merchantList = new HashMap<>();
    HashMap<String, Account> customerList = new HashMap<>();
    HashMap<String, String> testList = new HashMap<>();



    public AccountService(MessageQueue q) {
        queue = q;
//        queue.addHandler("MerchantRegister", this::merchantRegister);
        queue.addHandler("MerchantRegister", this::merchantRegister);
        queue.addHandler("CustomerRegister", this::customerRegister);
        queue.addHandler("GetMerchantList", this::getMerchantList);
        queue.addHandler("GetCustomerList", this::getCustomerList);
        queue.addHandler("GetSpecificMerchant", this::customerRegister);
        queue.addHandler("GetSpecificCustomer", this::customerRegister);
    }

    public void merchantRegisterTest(Event event) {
        System.out.println("in event");
        String acc = event.getArgument(0,String.class);
        System.out.println(acc);
        String id = idGenerator.generateID("m");
        System.out.println(acc + " " + id);
        testList.put(id, acc);
        Event tempEvent = new Event("MerchantRegisteredSuccessfully", new Object[] {id});
        System.out.println("Event published");
        queue.publish(tempEvent);
    }

    public void merchantRegister(Event event) {
        try {
            Account acc = event.getArgument(0, Account.class);
            System.out.println("checking if bankid: " +acc.getBankID() + " exists");
            bankService.getAccount(acc.getBankID());
            System.out.println("Bank id found! Creating user");
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
        }

    }

    public void customerRegister(Event event) {
//        System.out.println("printing event details: " + event.toString());
        Account acc = event.getArgument(0, Account.class);
//        System.out.println("Printing events converted to acc: " + acc.toString());
        String id = idGenerator.generateID("c");
        acc.setRoleID(id);
        customerList.put(id, acc);
        Event tempEvent = new Event("CustomerRegisteredSuccessfully", new Object[] {id});
        queue.publish(tempEvent);
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
}
