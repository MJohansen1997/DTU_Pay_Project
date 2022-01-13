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
        queue.addHandler("MerchantList", this::customerRegister);
        queue.addHandler("CustomerList", this::customerRegister);

    }

    public void merchantRegister(Event event) {
        Account acc = event.getArgument(0,Account.class);
        String id = idGenerator.generateID("m");
        merchantList.put(id, acc);
        Event mercReg = new Event("MerchantRegisteredSuccessfully", new Object[] {id});
        queue.publish(mercReg);
    }
    public void customerRegister(Event event) {
        Account acc = event.getArgument(0,Account.class);
        String id = idGenerator.generateID("c");
        customerList.put(id, acc);
    }

    public void getCustomerList() {

    }
    public void getMerchantList() {

    }
}
