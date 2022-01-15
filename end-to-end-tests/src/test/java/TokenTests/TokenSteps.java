package TokenTests;

import CustomerAPI.CustomerAPI;
import DTO.RegistrationDTO;
import DTO.UserDTO;
import MerchantAPI.MerchantAPI;
import dtu.ws.fastmoney.BankServiceException_Exception;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class TokenSteps {
    String cid;
    String mid;
    CustomerAPI cAPI = new CustomerAPI();
    MerchantAPI mAPI = new MerchantAPI();
    String token;
    int oldCount;
    int newCount;
    String message;


    @Given("the customer is registered with DTU pay")
    public void that_the_customer_is_registered_with_dtu_pay() {
        cAPI.setDTUPayID(cAPI.registerCustomer(new UserDTO("Token", "Tokenson", "123499-1234", cAPI.getBankID())));
    }

    @Given("the customer has {int} token left")
    public void the_customer_has_token_left(Integer int1) {
        // Make {int} ammount of payments
    }
    @When("the customer request new Tokens")
    public void the_customer_request_new_tokens() {
        oldCount = cAPI.getTokens().size();
        message = cAPI.requestTokens(cAPI.getDTUPayID());
    }
    @Then("the customer receives {int} new tokens")
    public void the_customer_receives_new_tokens(Integer int1) {
        newCount = cAPI.getTokens().size();
        assertEquals(int1, newCount - oldCount);
    }

    @Then("the customer receive the following message {string}")
    public void the_customer_receive_the_following_message(String string) {
        assertEquals(message, string);
    }

    @Given("a customer with a bank account with balance {double}")
    public void aCustomerWithABankAccountWithBalance(double balance) {
        try {
            cAPI.createAccount("Token", "Tokenson", "123499-1234", BigDecimal.valueOf(balance));
        }
        catch (Exception ignored){
            System.out.println(ignored.getMessage());
        }
    }


    @After("@token")
    public void cleanUpAccounts() throws BankServiceException_Exception {
        cAPI.retireAccount();
    }
}
