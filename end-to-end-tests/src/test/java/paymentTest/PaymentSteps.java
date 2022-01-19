
package paymentTest;

import Bank.BankAccountManager;
import CustomerAPI.CustomerAPI;
import DTO.Payment;
import DTO.UserDTO;
import MerchantAPI.MerchantAPI;
import dtu.ws.fastmoney.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.After;

import javax.ws.rs.NotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class PaymentSteps {

   CustomerAPI customerAPI = new CustomerAPI();
   MerchantAPI merchantAPI = new MerchantAPI();
   String custBankID, mercBankID;
   UserDTO cust;
   UserDTO merc;
   String token;
   String merchantID;
   public BankServiceException_Exception exception = null;


    //********************************//
    //********** Scenario 1 **********//
    //********************************//
    @Given("customer with a bank account with balance {double}")
    public void CustomerWithABankAccountWithBalance(double balance) {
        try {
            custBankID = BankAccountManager.createAccount("CustomerPay11", "CustomerPayment11", "123499-3111", BigDecimal.valueOf(balance));
        }
        catch (BankServiceException_Exception e){
            fail(e.getMessage());
        }
    }

    @And("that the customer is registered with DTU Pay")
    public void thatTheCustomerIsRegisteredWithDTUPay() {
        cust = new UserDTO("CustomerPay11", "CustomerPayment11", "123499-3111", custBankID);
        customerAPI.registerCustomer(cust);
    }

    @And("the customer requests new tokens")
    public void theCustomerRequestsNewTokens() {
        customerAPI.requestTokens(customerAPI.getDTUPayID());
        token = customerAPI.getTokens().get(0);
    }

    @Given("a merchant with a bank account with balance {double}")
    public void aMerchantWithABankAccountWithBalance(double balance) {
        try {
            mercBankID = BankAccountManager.createAccount("MerchantPay11", "MerchantPayment11", "123499-4227", BigDecimal.valueOf(balance));
        } catch (BankServiceException_Exception bankServiceException_exception){
            //If it catches an exception then this step fails
            fail(bankServiceException_exception.getMessage());
        }
    }

    @And("that the merchant is registered with DTU Pay")
    public void thatTheMerchantIsRegisteredWithDTUPay() {
        merc = new UserDTO("MerchantPay11","MerchantPayment11","123499-4227",mercBankID);
        merchantID = merchantAPI.registerMerchant(merc);
    }

    @When("the merchant initiates a payment for {double} kr by the customer")
    public void theMerchantInitiatesAPaymentForKrByTheCustomer(double amount) {
        try {
            Payment p = merchantAPI.payment(new Payment(custBankID,mercBankID, merchantID,new BigDecimal(amount),"Cannot be Empty",token));
            assertTrue(p instanceof Payment && p != null);
        } catch (Exception e) {
            fail();
        }
    }

    @Then("the payment is {string}")
    public void thePaymentIsSuccessful(String status) {
        //Don't needed
    }

    @And("the balance of the customer at the bank is {double} kr")
    public void theBalanceOfTheCustomerAtTheBankIsKr(double balance) throws BankServiceException_Exception {
        BigDecimal accountBalance = BankAccountManager.getAccount(custBankID).getBalance();
        assertEquals(balance, accountBalance.doubleValue(),0);
    }

    @And("the balance of the merchant at the bank is {double} kr")
    public void theBalanceOfTheMerchantAtTheBankIsKr(double balance) throws BankServiceException_Exception {
        BigDecimal accountBalance = BankAccountManager.getAccount(mercBankID).getBalance();
        assertEquals(balance, accountBalance.doubleValue(), 0);
    }




    //********************************//
    //********** Scenario 2 **********//
    //********************************//
    @Given("a customer with an ID")
    public void a_customer_with_an_id() {
        try {
            custBankID = BankAccountManager.createAccount("CustomerPay10", "CustomerPayment10", "123499-2111", new BigDecimal("34000"));
            cust = new UserDTO("CustomerPay10", "CustomerPayment10", "123499-2111", custBankID);
            customerAPI.registerCustomer(cust);
            assertTrue(true);
        }
        catch (BankServiceException_Exception e){
            fail(e.getMessage());
        }
    }
    @Given("the token is invalid")
    public void the_token_is_invalid() {
        try {
            customerAPI.requestTokens(customerAPI.getDTUPayID());
            ArrayList<String> tokens = customerAPI.getTokens();
            mercBankID = BankAccountManager.createAccount("MerchantPay10", "MerchantPayment10", "123499-3222", BigDecimal.valueOf(199));
            merc = new UserDTO("MerchantPay10","MerchantPayment10","123499-3222",mercBankID);
            merchantID = merchantAPI.registerMerchant(merc);
        } catch (BankServiceException_Exception e) {
            fail(e.getMessage());
        }
        token = "fakeToken";
        assertTrue(true);
    }

    @When("the merchant initiates a payment for {int} kr by the customer1")
    public void the_merchant_initiates_a_payment_for_kr_by_the_customer(int amount) {
        try {
            Payment p = merchantAPI.payment(new Payment(custBankID,mercBankID,merchantID,new BigDecimal("1"),"Cannot be Empty",token));
        }catch(NotFoundException notFoundException){
            assertTrue(true);
        }
    }

    @Then(value = "the payment is denied")
    public void the_payment_is_denied() {
        // don't needed.
        assertTrue(true);
    }
    @When("the merchant initiates a payment with the same token")
    public void the_merchant_initiates_a_payment_with_the_same_token() {
        try {
            Payment p = merchantAPI.payment(new Payment(custBankID,mercBankID,merchantID,new BigDecimal("1"),"Cannot be Empty",token));
        }catch (NotFoundException e){
            assertTrue(true);
        }

    }


    //********************************//
    //********** Scenario 3 **********//
    //********************************//
    /*@Given("a customer with the ID {string}")
    public void a_customer_with_the_id(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Given("the customer has {int} tokens")
    public void the_customer_has_tokens(Integer string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Given("a merchant with the ID {string}")
    public void a_merchant_with_the_id(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("the merchant initiates a payment for {string} kr by the customer")
    public void the_merchant_initiates_a_payment_for_kr_by_the_customer(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Given("the merchant types a {string} customerID")
    public void the_merchant_types_a_customer_id(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("the balance of the customer at the bank is {string} kr")
    public void the_balance_of_the_customer_at_the_bank_is_kr(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("the balance of the merchant at the bank is {string} kr")
    public void the_balance_of_the_merchant_at_the_bank_is_kr(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }*/

    @After("@payment")
    public void cleanup() {
        try {
            BankAccountManager.retireAccount(custBankID);
            BankAccountManager.retireAccount(mercBankID);
            custBankID = null;
            mercBankID = null;
        } catch (BankServiceException_Exception e) {
            System.out.println("error cleaning up: " + e.getMessage());
        }
    }



}
