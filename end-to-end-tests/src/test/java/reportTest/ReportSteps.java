package reportTest;

import CustomerAPI.CustomerAPI;
import DTO.ManagerReportDTO;
import DTO.ReportList;
import ManagerAPI.ManagerAPI;
import MerchantAPI.MerchantAPI;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Before;

import static org.junit.jupiter.api.Assertions.*;

public class ReportSteps {

    ManagerReportDTO dto;
    ManagerAPI mAPI = new ManagerAPI();
    MerchantAPI merAPI = new MerchantAPI();
    CustomerAPI cusAPI = new CustomerAPI();
    ReportList rList = new ReportList();


    //  Scenario: The Report for the manager
    @Given("The user is a {string}")
    public void theUserIsAManager(String user) {
        assertEquals("manager", user);
    }

    @Then("the {string} request the reportlist")
    public void theManagerRequestTheReportlist(String user) {
        if (user.equals("manager"))
            rList = mAPI.requestReports();
    }

    @And("the reportlist should contain all reports")
    public void theReportlistShouldContain(ReportList reportList) {
        assertEquals(rList.getReportList().size(), reportList.getReportList().size());
    }
    //  Scenario END

    //  Scenario: Two reports are created after a transaction
    @Given("a transaction succeded")
    public void aTransactionSucceded() {
    }

    @Then("a customer report is created for the transaction")
    public void aCustomerReportIsCreatedForTheTransaction() {
    }

    @And("a merchant report is created for the transaction")
    public void aMerchantReportIsCreatedForTheTransaction() {
    }
    //  Scenario END

    //  Scenario: The report for the customer

    @Before("@report")
    public void setupTransaction() {

    }

    @Given("the user is a {string}")
    public void theUserIsACustomer(String user) {
        assertEquals("customer", user);
    }

    @Then("the {string} requests the report")
    public void theRequestsTheReport(String user) {
    }

    @And("the report should contain all of the {string} transaction details")
    public void theReportShouldContainAllOfTheTransactionDetails(String user) {
    }
    //  Scenario END

    //  Scenario: The report for the merchant

    @Before("@report")
    public void setupTransaction() {

    }

    @Given("the user is a {string}")
    public void theUserIsAMerchant(String user) {
        assertEquals("merchant", user);
    }

    @Then("the {string} request the reportlist")
    public void theMerchantRequestTheReportlist(String merchant) {
        merAPI.requestMerchantReports();
    }

    @And("the report should contain all of the {string} transaction details")
    public void theReportListContainsAllReports(String user) {
    }
    //  Scenario END
}
