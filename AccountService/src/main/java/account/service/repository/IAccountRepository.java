package account.service.repository;

import account.service.DTO.Account;

import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;

public interface IAccountRepository {
    void updateList();
    Account getSpecificUser(String userId, String role) throws InvalidPropertiesFormatException;
    Boolean isBankIdAlreadyAssigned(String bankId, String role) throws InvalidPropertiesFormatException;

}
