package paymentTest;

import CustomerAPI.CustomerAPI;
import dtu.ws.fastmoney.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class PaymentSteps {

    CustomerAPI customerAPI = new CustomerAPI();

    @Given("a customer with a bank account with balance {double}")
    public void aCustomerWithABankAccountWithBalance(double balance) {
        try {
            customerAPI.createAccount("Test", "Testessen", "", new BigDecimal(balance));
        } catch (BankServiceException_Exception e) {
            e.printStackTrace();
        }
    }

    @And("that the customer is registered with DTU Pay")
    public void thatTheCustomerIsRegisteredWithDTUPay() {
        customerAPI.register();
    }

    @And("the customer has {int} tokens")
    public void theCustomerHasTokens(int tokenAmount) {
        assertEquals(customerAPI.getTokens().size(), tokenAmount);
    }

    @Given("a merchant with a bank account with balance {BigDecimal}")
    public void aMerchantWithABankAccountWithBalance(BigDecimal balance) {
        assertEquals(customerAPI.getBalance(), balance);
    }

    @And("that the merchant is registered with DTU Pay")
    public void thatTheMerchantIsRegisteredWithDTUPay() {
        customerAPI.register();
    }

    @When("the merchant initiates a payment for {BigDecimal} kr by the customer")
    public void theMerchantInitiatesAPaymentForKrByTheCustomer(BigDecimal amount) {
        try {
            customerAPI.transferMoney();
            assertTrue(true);
        } catch (BankServiceException_Exception e) {
            //This will make it fail if any exception is catched
            assertTrue(false);
        }
    }

    @Then("the payment is {string}")
    public void thePaymentIsSuccessful() {
        //successful
    }

    @And("the balance of the customer at the bank is {double} kr")
    public void theBalanceOfTheCustomerAtTheBankIsKr(double balance) {
    }

    @And("the balance of the merchant at the bank is {double} kr")
    public void theBalanceOfTheMerchantAtTheBankIsKr(double balance) {
    }

    @Given("a customer with the ID {string}")
    public void aCustomerWithTheID(String customerID) {
    }

    @And("a merchant with the ID {string}")
    public void aMerchantWithTheID(String merchantID) {
    }

    @Given("the merchant select the customer ID, which is {string}")
    public void theMerchantSelectTheCustomerIDWhichIs(String customerID) {
    }

    @Then("the payment is {string}")
    public void thePaymentIsDenied(String status) {
    }
}
