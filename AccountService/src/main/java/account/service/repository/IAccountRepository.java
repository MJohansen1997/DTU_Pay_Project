package account.service.repository;

import account.service.DTO.Account;

import javax.ws.rs.NotFoundException;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;

public interface IAccountRepository {
    void addNewUser(Account account, String userId) throws IllegalArgumentException;
    Account getSpecificUser(String userId) throws NotFoundException;
    HashMap<String, Account> getUserList();
}
