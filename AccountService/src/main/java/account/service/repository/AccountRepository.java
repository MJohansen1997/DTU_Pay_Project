package account.service.repository;

import account.service.DTO.Account;
import com.sun.tools.ws.wsdl.document.jaxws.Exception;
import io.vertx.core.cli.InvalidValueException;
import lombok.Getter;
import lombok.Setter;
import messaging.Event;

import javax.ws.rs.NotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InvalidPropertiesFormatException;

public class AccountRepository implements IAccountRepository {

    //We're storing our users and bank ids
    @Getter @Setter HashMap<String, Account> userList = new HashMap<>();
    @Getter @Setter HashSet<String> bankIds = new HashSet<>();

    //Adds a user to our internal storage
    @Override
    public void addNewUser(Account account, String userId) throws IllegalArgumentException {
        if(bankIds.contains(account.getBankID()))
            throw new IllegalArgumentException("BankIdNotFound");
        else {
            bankIds.add(account.getBankID());
            userList.put(userId, account);
        }
    }

    //Finds a specific user from our internal storage
    @Override
    public Account getSpecificUser(String userId) throws NotFoundException {
        if(userList.containsKey(userId))
            return userList.get(userId);
        else
            throw new NotFoundException("UserNotFound");
    }
}
