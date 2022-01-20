package account.service.repository;

import account.service.DTO.Account;
import account.service.port.IExternalService;
import account.service.exception.BankIdAlreadyRegisteredException;

import java.util.HashMap;

//Middle man to communicate with our AccountService
public class AccountRepositoryAdapter implements IExternalService {
    IAccountRepository repo = new AccountRepository();

    //Accesses our local storage and adds the new user
    @Override
    public void registerUser(Account acc, String userId) throws BankIdAlreadyRegisteredException {
        try {
            repo.addNewUser(acc, userId);
        } catch (IllegalArgumentException e) {
            throw new BankIdAlreadyRegisteredException(e.getMessage());
        }
    }

    //Searches for user in local storage
    @Override
    public Account getSpecificUser(String userId) {
        return repo.getSpecificUser(userId);
    }

    //Gets all users in the userList
    @Override
    public HashMap<String, Account> getUserList() {
        return repo.getUserList();
    }

}
