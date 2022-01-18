package reportTest;

import CustomerAPI.CustomerAPI;
import DTO.ManagerReportDTO;
import DTO.Report.ReportList;
import DTO.Report.ReportManager;
import ManagerAPI.ManagerAPI;
import MerchantAPI.MerchantAPI;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Before;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ReportSteps {

    ManagerReportDTO dto;
    ManagerAPI mAPI = new ManagerAPI();
    MerchantAPI merAPI = new MerchantAPI();
    CustomerAPI cusAPI = new CustomerAPI();
    ReportList rList = new ReportList();

    @Given("the manager request the reportlist")
    public void theManagerRequestTheReportlist() {
        rList = mAPI.requestManagerReports();
    }

    @Then("the reportlist should contain two reports with the same paymentID")
    public void theReportlistShouldContainTwoReportsWithTheSamePaymentIDWhichIs() {
       ArrayList<ReportManager> manArray = rList.getReportList();
        assertEquals(manArray.get(0).getPaymentID(), manArray.get(1).getPaymentID());
    }

    @Given("a report has paymentID")
    public void aReportHasPaymentID() {
        mAPI.requestCustomerReports().getPaymentID();
    }

    @And("the report has a customerID")
    public void theReportHasACustomerID() {
        mAPI.requestCustomerReports().getCustomerID();
    }

    @Then("the report is a customer report")
    public void theReportIsACustomerReport() {
        mAPI.requestManagerReports().getReportList().get(0);
    }

    //not done
    @And("the report doesnt have a customerID")
    public void theReportDoesntHaveACustomerID() {
        assertFalse(mAPI.requestCustomerReports().getCustomerID().equals(mAPI.requestMerchantReports().getMerchantID()));
    }

    @Then("the report is a merchant report")
    public void theReportIsAMerchantReport() {
        mAPI.requestManagerReports().getReportList().get(1);
    }
}
