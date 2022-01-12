package RegisterTest;

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
    BankService bank = new BankServiceService().getBankServicePort();
    String bankID;
    String roleID;
    UserDTO userDTO;
    CustomerAPI cust;
    MerchantAPI merc;

    @Before
    public void setupAccount() {
        ObjectFactory objFac = new ObjectFactory();
        User user = objFac.createUser();

        user.setCprNumber("12345");
        user.setFirstName("Merchant");
        user.setLastName("Testing");
        try {
            bankID = bank.createAccountWithBalance(user, BigDecimal.valueOf(1000));
        } catch (BankServiceException_Exception e) {
            System.out.println("error setting up");
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
    public void isRegisteredWithDTUPayAsACustomer() {
        cust = new CustomerAPI();
        roleID = cust.registerCustomer(userDTO);
    }

    @Then("is registered with DTUPay as a merchant")
    public void isRegisteredWithDTUPayAsAMerchant() {
        merc = new MerchantAPI();
        roleID = merc.registerMerchant(userDTO);
    }

    @And("is given a customer id")
    public void isGivenACustomerId() {
        assertTrue(cust.getCustomerList().containsKey(roleID));
    }

    @And("is given a Merchant id")
    public void isGivenAMerchantId() {
        assertTrue(merc.getMerchantList().containsKey(roleID));
    }


    @After
    public void cleanUp() {
        try {
            bank.retireAccount(bankID);
        } catch (BankServiceException_Exception e ) {
            System.out.println("error cleaning up");
        }
    }



}
