package account.service.port;

import account.service.DTO.Account;
import account.service.exception.BankIdAlreadyRegisteredException;
import account.service.exception.RepositoryException;
import account.service.exception.UserNotFoundException;

import java.util.HashMap;

public interface IExternalService {
    void registerUser(Account acc, String userId) throws BankIdAlreadyRegisteredException;
    Account getSpecificUser(String userId) throws UserNotFoundException;
    HashMap<String, Account> getUserList();
}
