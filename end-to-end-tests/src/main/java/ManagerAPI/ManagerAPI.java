package ManagerAPI;

import DTO.Report.Report;
import DTO.Report.ReportList;


import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class ManagerAPI {
    Client client = ClientBuilder.newClient();

    ReportList reportsManager = new ReportList();
    Report reportsMerchant;
    Report reportsCustomer;


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
}
