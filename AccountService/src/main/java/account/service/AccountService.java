package account.service;

import account.service.DTO.Account;
import messaging.Event;
import messaging.MessageQueue;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

public class AccountService {

    MessageQueue queue;
    CompletableFuture<String> testFuture = new CompletableFuture<>();
    HashMap<String, Account> merchantList = new HashMap<>();
    HashMap<String, Account> customerList = new HashMap<>();
    

    public AccountService(MessageQueue q) {
        queue = q;
        queue.addHandler("MerchantRegister", this::merchantRegister);
        queue.addHandler("CustomerRegister", this::customerRegister);
        queue.addHandler("GetMerchantList", this::getMerchantList);
        queue.addHandler("GetCustomerList", this::getCustomerList);
        queue.addHandler("GetSpecificMerchant", this::customerRegister);
        queue.addHandler("GetSpecificCustomer", this::customerRegister);
    }

    public void merchantRegister(Event event) {
        Account acc = event.getArgument(0,Account.class);
        String id = idGenerator.generateID("m");
        merchantList.put(id, acc);
        Event tempEvent = new Event("MerchantRegisteredSuccessfully", new Object[] {id});
        queue.publish(tempEvent);
    }
    public void customerRegister(Event event) {
        Account acc = event.getArgument(0,Account.class);
        String id = idGenerator.generateID("c");
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
