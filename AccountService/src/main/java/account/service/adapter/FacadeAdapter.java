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
    MessageQueue queue;
    AccountService accountService;

    public FacadeAdapter(MessageQueue q, AccountService as) {
        queue = q;
        this.accountService = as;
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
}
