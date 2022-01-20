package account.service.adapter;

import account.service.AccountService;
import account.service.DTO.Account;
import account.service.exception.BankIdAlreadyRegisteredException;
import account.service.exception.BankIdNotFoundException;
import account.service.exception.InvalidRegistrationInputException;
import account.service.exception.UserNotFoundException;
import messaging.Event;
import messaging.MessageQueue;

public class FacadeAdapter {
    MessageQueue queue;
    AccountService accountService;

    //Default constructor to initiate queue and AccountService
    public FacadeAdapter(MessageQueue q, AccountService as) {
        queue = q;
        this.accountService = as;

        /* Adding Handlers */
        queue.addHandler("UserRegister", this::registerUser);
        queue.addHandler("GetSpecificUserById", this::getSpecificUser);

    }

    //Handler which communicates with our Facade when
    public void registerUser(Event event) {
        Account acc = event.getArgument(0, Account.class);
        String role = event.getArgument(1, String.class);
        try {
            String id = accountService.registerUser(acc, role);
            String complete = role+"RegisteredSuccessfully";
            System.out.println(complete);
            Event temp = new Event(complete, new Object[] {id});
            queue.publish(temp);
        } catch (BankIdAlreadyRegisteredException | InvalidRegistrationInputException | BankIdNotFoundException e) {
            String incomplete = role+"UnsuccessfulRegistration";
            Event temp = new Event(incomplete, new Object[] {e.getMessage()});
            queue.publish(temp);
        }
    }

    //Handles the event of someone wanting to get a specific user
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
