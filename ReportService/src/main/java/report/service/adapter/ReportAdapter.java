package report.service.adapter;

import messaging.Event;
import messaging.MessageQueue;
import report.service.DTO.ReportRequest;
import report.service.exception.IncorrectInformationException;
import report.service.ReportService;
import report.service.port.IReportService;

public class ReportAdapter {

    MessageQueue queue;
    IReportService service;

    public ReportAdapter(MessageQueue q, ReportService service) {
        queue = q;
        queue.addHandler("CustomerRegisteredSuccessfully", this::handleCreateUserInReportRegister);
        queue.addHandler("ReportCreationRequest", this::handleCreateReport);
        queue.addHandler("ReportManager", this::handleReportManagerRequest);
        queue.addHandler("CustomerReportsRequest", this::handleCustomerReportRequest);
        queue.addHandler("MerchantReportsRequest", this::handleMerchantReportRequest);
        queue.addHandler("MerchantRegisteredSuccessfully", this::handleCreateUserInReportRegister);
        this.service = service;
    }

    public void handleCreateUserInReportRegister(Event event){
        var userID = event.getArgument(0, String.class);
        service.createUser(userID);
    }


    public void handleCreateReport(Event event){
        var s = event.getArgument(0, ReportRequest.class);
        Event returnEvent;
        try {
            returnEvent = new Event("ReportCreationRequestSucceeded", new Object[] {service.createReport(s)});
            queue.publish(returnEvent);
        } catch (IncorrectInformationException e) {
            returnEvent = new Event("ReportCreationRequestFailed", new Object[] {e.getMessage()});
            queue.publish(returnEvent);
        }
    }

    public void handleReportManagerRequest(Event event){
        Event returnEvent;
        returnEvent = new Event("ManagerReportRequest", new Object[] {service.getManagerReports()});
        queue.publish(returnEvent);
    }
    public void handleCustomerReportRequest(Event event){
        var userID = event.getArgument(0, String.class);
        Event returnEvent;
        try {
            returnEvent = new Event("CustomerReportsSent", new Object[] {service.getReportsByID(userID)});
            queue.publish(returnEvent);
        } catch (IncorrectInformationException e) {
            returnEvent = new Event("CustomerReportsNotSent", new Object[] {e.getMessage()});
            queue.publish(returnEvent);
        }
    }

    public void handleMerchantReportRequest(Event event){
        var userID = event.getArgument(0, String.class);
        Event returnEvent;
        try {
            returnEvent = new Event("MerchantReportsSent", new Object[] {service.getReportsByID(userID)});
            queue.publish(returnEvent);
        } catch (IncorrectInformationException e) {
            returnEvent = new Event("MerchantReportsNotSent", new Object[] {e.getMessage()});
            queue.publish(returnEvent);
        }
    }
}
