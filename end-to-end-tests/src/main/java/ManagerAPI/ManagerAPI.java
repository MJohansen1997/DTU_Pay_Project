package ManagerAPI;

import DTO.Report.ReportCustomer;
import DTO.Report.ReportList;
import DTO.Report.ReportMerchant;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class ManagerAPI {
    Client client = ClientBuilder.newClient();

    ReportList reportsManager = new ReportList();
    ReportMerchant reportsMerchant = new ReportMerchant();
    ReportCustomer reportsCustomer = new ReportCustomer();


    public ReportList requestManagerReports() {
        WebTarget target = client.target("http://localhost:8080/manager/reports");
        try {
            reportsManager = target.request()
                    .accept(MediaType.APPLICATION_JSON)
                    .get(ReportList.class);
            return reportsManager;
        } catch (NotFoundException exception) {
            //How does we handle exceptions here? HTTP or Custom exceptions?
            throw new NotFoundException("Reports doesn't exist");
        }
    }

    public ReportMerchant requestMerchantReports() {
        WebTarget target = client.target("http://localhost:8080/merchant/reports");
        try {
            reportsMerchant = target.request()
                    .accept(MediaType.APPLICATION_JSON)
                    .get(ReportMerchant.class);
            return reportsMerchant;
        } catch (NotFoundException exception) {
            //How does we handle exceptions here? HTTP or Custom exceptions?
            throw new NotFoundException("Reports doesn't exist");
        }
    }

    public ReportCustomer requestCustomerReports() {
        WebTarget target = client.target("http://localhost:8080/customer/reports");
        try {
            reportsCustomer = target.request()
                    .accept(MediaType.APPLICATION_JSON)
                    .get(ReportCustomer.class);
            return reportsCustomer;
        } catch (NotFoundException exception) {
            //How does we handle exceptions here? HTTP or Custom exceptions?
            throw new NotFoundException("Reports doesn't exist");
        }
    }

//    public void createRapport(String paymentID){
//        Report report;
//        report.setPaymentID(paymentID);
//
//
//    }
}
