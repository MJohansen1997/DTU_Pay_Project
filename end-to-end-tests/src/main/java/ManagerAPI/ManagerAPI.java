package ManagerAPI;

import DTO.*;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Collection;

public class ManagerAPI {
    Client client = ClientBuilder.newClient();

    ReportList reports = new ReportList();

    public ReportList requestReports() {
        WebTarget target = client.target("http://localhost:8080/manager/reports");
        try {
            reports = target.request()
                    .accept(MediaType.APPLICATION_JSON)
                    .get(ReportList.class);
            return reports;
        } catch (NotFoundException exception) {
            //How does we handle exceptions here? HTTP or Custom exceptions?
            throw new NotFoundException("Reports doesn't exist");
        }
    }
}
