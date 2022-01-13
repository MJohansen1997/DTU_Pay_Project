//package paymentTest;
//
//import Bank.BankAccountManager;
//import CustomerAPI.CustomerAPI;
//import DTO.UserDTO;
//import MerchantAPI.MerchantAPI;
//import dtu.ws.fastmoney.*;
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import io.cucumber.java.After;
//
//import java.math.BigDecimal;
//
//import static org.junit.Assert.*;
//
//public class PaymentSteps {
//
//    CustomerAPI customerAPI = new CustomerAPI();
//    MerchantAPI merchantAPI = new MerchantAPI();
//    String custBankID, mercBankID;
//    UserDTO cust;
//    UserDTO merc;
//
//    @Given("a customer with a bank account with balance {double}")
//    public void aCustomerWithABankAccountWithBalance(double balance) {
//        try {
//            custBankID = BankAccountManager.createAccount("cust", "Testessen", "515", BigDecimal.valueOf(balance));
//            mercBankID = BankAccountManager.createAccount("merc", "Testessen", "516", BigDecimal.valueOf(balance));
//        } catch (BankServiceException_Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @And("that the customer is registered with DTU Pay")
//    public void thatTheCustomerIsRegisteredWithDTUPay() {
//        cust = new UserDTO("cust","Testessen","515", custBankID);
//        customerAPI.registerCustomer(cust);
//    }
//
//    @And("the customer has {int} tokens")
//    public void theCustomerHasTokens(int tokenAmount) {
//        assertEquals(customerAPI.getTokens().size(), tokenAmount);
//    }
//
//    @Given("a merchant with a bank account with balance {BigDecimal}")
//    public void aMerchantWithABankAccountWithBalance(BigDecimal balance) throws BankServiceException_Exception {
//        merc = new UserDTO("merc", "Testessen", "516", mercBankID);
//        assertEquals(BankAccountManager.getBalance(merc.getBankID()), balance);
//    }
//
//    @And("that the merchant is registered with DTU Pay")
//    public void thatTheMerchantIsRegisteredWithDTUPay() {
//        merchantAPI.registerMerchant(cust);
//    }
//
//    @When("the merchant initiates a payment for {BigDecimal} kr by the customer")
//    public void theMerchantInitiatesAPaymentForKrByTheCustomer(BigDecimal amount) {
//        try {
//            BankAccountManager.transferMoney(custBankID,mercBankID,amount,"Test transfer");
//            assertTrue(true);
//        } catch (BankServiceException_Exception e) {
//            //This will make it fail if any exception is catched
//            fail();
//        }
//    }
//
//    @Then("the payment is {string}")
//    public void thePaymentIsSuccessful() {
//        //successful
//    }
//
//    @And("the balance of the customer at the bank is {double} kr")
//    public void theBalanceOfTheCustomerAtTheBankIsKr(double balance) {
//    }
//
//    @And("the balance of the merchant at the bank is {double} kr")
//    public void theBalanceOfTheMerchantAtTheBankIsKr(double balance) {
//    }
//
//    @Given("a customer with the ID {string}")
//    public void aCustomerWithTheID(String customerID) {
//    }
//
//    @And("a merchant with the ID {string}")
//    public void aMerchantWithTheID(String merchantID) {
//    }
//
//    @Given("the merchant select the customer ID, which is {string}")
//    public void theMerchantSelectTheCustomerIDWhichIs(String customerID) {
//    }
//
//    @Then("the payment is {string}")
//    public void thePaymentIsDenied(String status) {
//    }
//
//    @After("@payment")
//    public void cleanup() {
//        try {
//            BankAccountManager.retireAccount(custBankID);
//            BankAccountManager.retireAccount(mercBankID);
//        } catch (BankServiceException_Exception e ) {
//            System.out.println("error cleaning up: " + e.getMessage());
//        }
//    }
//}
