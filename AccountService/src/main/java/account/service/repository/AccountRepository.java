package account.service.repository;

import account.service.DTO.Account;
import lombok.Getter;
import lombok.Setter;
import messaging.Event;

import java.util.HashMap;
import java.util.HashSet;
import java.util.InvalidPropertiesFormatException;


public class AccountRepository implements IAccountRepository {
    @Getter @Setter HashMap<String, Account> merchantList = new HashMap<>();
    @Getter @Setter HashMap<String, Account> customerList = new HashMap<>();
    @Getter @Setter HashSet<String> bankIds = new HashSet<>();
    

    @Override
    public void updateList(Account account) {

    }

    @Override
    public Account getSpecificUser(String userId, String role) throws InvalidPropertiesFormatException {
        if(role.equals("m"))
            return merchantList.get(userId);
        if(role.equals("c"))
            return customerList.get(userId);
        throw new InvalidPropertiesFormatException("User not found");
    }

    @Override
    public Boolean isBankIdAlreadyAssigned(String bankId, String role) {
        return null;
    }
}
