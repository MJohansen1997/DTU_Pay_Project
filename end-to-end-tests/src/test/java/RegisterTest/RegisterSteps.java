package RegisterTest;

import Bank.BankAccountManager;
import CustomExceptions.CustomRegistrationException;
import CustomerAPI.CustomerAPI;
import DTO.UserDTO;
import MerchantAPI.MerchantAPI;
import dtu.ws.fastmoney.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;



public class RegisterSteps {
    String bankID;
    String roleID;
    UserDTO userDTO;
    CustomerAPI cust =  new CustomerAPI();
    MerchantAPI merc = new MerchantAPI();
    String registerID;

    @Before("@register")
    public void setupAccount() throws BankServiceException_Exception {
        try {
            bankID = BankAccountManager.createAccount("Merchant", "Testing","bonkobonko", new BigDecimal(69));
        } catch (BankServiceException_Exception e) {
            System.out.println("error setting up: " + e.getMessage());
        }
    }

    @Given("has a bank account with a valid bank account id")
    public void hasABankAccountWithAValidBankAccountId() {
        assertNotNull(bankID);
    }

    @And("provides their details to DTUPay starting with first name {string}, last name {string}, CPR {string} & Bank account id")
    public void providesTheirDetailsToDTUPayStartingWithFirstNameLastNameCPRBankAccountNr(String firstName, String lastName, String CPR) {
        userDTO = new UserDTO(firstName, lastName, CPR, bankID);
    }

    @Then("is registering with DTUPay as a customer")
    public void isRegisteredWithDTUPayAsACustomer() throws CustomRegistrationException {
        roleID = cust.registerCustomer(userDTO);
    }

    @Then("is registered with DTUPay as a merchant")
    public void isRegisteredWithDTUPayAsAMerchant() {
        roleID = merc.registerMerchant(userDTO);
    }

    @And("is given a customer id")
    public void isGivenACustomerId() {
        assertNotEquals("404", roleID);
        assertTrue(cust.getCustomerList().containsKey(roleID));
    }

    @And("is given a Merchant id")
    public void isGivenAMerchantId() {
        assertNotEquals("404", roleID);
        assertTrue(merc.getMerchantList().containsKey(roleID));
    }


    @Given("user gives non existing bank account id")
    public void userGivesNonExistingBankAccountId() {
        registerID = cust.registerCustomer(new UserDTO("cust", "testing no valid bank account","2234","0"));
    }

    @Given("user gives empty input")
    public void userGivesEmptyInput() {
        registerID = cust.registerCustomer(new UserDTO("", "no first name","2234","1"));

    }

    @Then("is given an error message")
    public void isGivenAnErrorMessage() {
        assertEquals("404", registerID);
    }

    @After("@register")
    public void cleanUp() {
        try {
            BankAccountManager.retireAccount(bankID);
        } catch (BankServiceException_Exception e ) {
            System.out.println("error cleaning up: " + e.getMessage());
        }
    }
}
