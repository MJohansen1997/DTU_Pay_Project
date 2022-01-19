package reportTest;

import Bank.BankAccountManager;
import CustomerAPI.CustomerAPI;
import DTO.ManagerReportDTO;
import DTO.Payment;
import DTO.Report.ReportList;
import DTO.UserDTO;
import ManagerAPI.ManagerAPI;
import MerchantAPI.MerchantAPI;
import dtu.ws.fastmoney.BankServiceException_Exception;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import report.service.DTO.Report;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ReportSteps {
    ArrayList<Report> merArray, cusArray;
    String token;
    ManagerReportDTO dto;
    ManagerAPI mAPI = new ManagerAPI();
    MerchantAPI merAPI = new MerchantAPI();
    CustomerAPI cusAPI = new CustomerAPI();
    UserDTO user1, user2;
    ReportList rList = new ReportList();
    String customerBankID, merchantBankID;
    String customerID, merchantID;
    Payment payDTO = new Payment("Stefan", "Shania", "merchantid1112", new BigDecimal(10000), "testpayment", "");

    @Before("@report")
    public void setupAccount() throws BankServiceException_Exception, InterruptedException {

        try {
            customerBankID = BankAccountManager.createAccount("Stefan", "Munkmand","bonkobonko", new BigDecimal(99999));
            merchantBankID = BankAccountManager.createAccount("Shania", "Haumand","bonkobingo", new BigDecimal(0));
        } catch (BankServiceException_Exception e) {
            System.out.println("error setting up: " + e.getMessage());
        }
        user1 = new UserDTO("Stefan", "Munkmand", "bonkobonko", customerBankID);
        user2 = new UserDTO("Shania", "Haumand", "bonkobingo", merchantBankID);

        merchantID = merAPI.registerMerchant(user2);
        customerID = cusAPI.registerCustomer(user1);

        cusAPI.requestTokens(customerID);
    }

    @Given("one completed transaction")
    public void aTransactionIsCompleted() {
        token = cusAPI.getTokens().get(0);
        // Stefan buys the sex 2.
        Payment p = merAPI.payment(new Payment(customerBankID,merchantBankID, merchantID,new BigDecimal(10000),"Cannot be Empty",token));
        assertNull(p.getErrorMessage());
        assertNotNull(merchantID);
        assertNotNull(customerID);
    }

    @And("the manager request the reportlist")
    public void theManagerRequestTheReportlist() {
        rList = mAPI.requestManagerReports();
        assertNotNull(rList);
    }

    @Then("the reportlist should contain two reports with the same tokenID")
    public void theReportlistShouldContainTwoReportsWithTheSamePaymentIDWhichIs() {
        ArrayList<Report> manArray = rList.getReportList();
        assertEquals(manArray.get(0).getTokenID(), manArray.get(1).getTokenID());
    }

    // END OF TEST 1 //

    @When("the customer requests their report")
    public void theCustomerRequestsTheirReport() {
        cusArray = cusAPI.requestCustomerReports(customerID).getReportList();
        assertNotNull(cusArray);
    }

    @And("the merchant requests their report")
    public void theMerchantRequestsTheirReport() {
        merArray = merAPI.requestMerchantReports(merchantID).getReportList();
        assertNotNull(merArray);
    }

    @Then("the customer recieves {int} report")
    public void theCustomerRecievesReport(int arg0) {
        assertEquals(1, cusArray.size());
    }

    @And("the merchant recivees {int} report")
    public void theMerchantReciveesReport(int arg0) {
        assertEquals(1, merArray.size());
    }

    // END OF TEST 2 //

    @After("@report")
    public void cleanUpReport() throws BankServiceException_Exception {
        BankAccountManager.retireAccount(customerBankID);
        BankAccountManager.retireAccount(merchantBankID);
    }

}
