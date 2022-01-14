package report.service;

import messaging.Event;
import messaging.MessageQueue;
import report.service.DTO.ReportRequest;

public class ReportService {

    MessageQueue queue;
    ReportRegister register = new ReportRegister();

    public ReportService(MessageQueue q) {
        queue = q;
        queue.addHandler("CreateUserInReportRegister", this::handleCreateUserInReportRegister);
        queue.addHandler("CreateReport", this::handleCreateReport);
        queue.addHandler("ReportManager", this::handleReportManager);
        queue.addHandler("ReportMerchant", this::handleReportMerchant);
        queue.addHandler("ReportCustomer", this::handleReportCustomer);
    }

    public void handleCreateUserInReportRegister(Event event){
        var s = event.getArgument(0, String.class);
        Event returnEvent;
        returnEvent = new Event("CreateUserInReportRegister", new Object[] {register.insertUser(s)});
        queue.publish(returnEvent);
    }


    public void handleCreateReport(Event event){
        var s = event.getArgument(0, ReportRequest.class);
        Event returnEvent;
        try {
            returnEvent = new Event("ReportCreated", new Object[] {register.createReport(s)});
            queue.publish(returnEvent);
        } catch (IncorrectInformationException e) {
            returnEvent = new Event("ReportNotCreated", new Object[] {e.getMessage()});
            queue.publish(returnEvent);
        }
    }

    public void handleReportManager(Event event){
        Event returnEvent;
        returnEvent = new Event("AllReportsRequestedSucceeded", new Object[] {register.getReportsForManager()});
        queue.publish(returnEvent);
    }
    public void handleReportMerchant(Event event){

        var s = event.getArgument(0, String.class);
        Event returnEvent;
        try {
            returnEvent = new Event("MerchantReportSent", new Object[] {register.getReportForMerchant(s)});
            queue.publish(returnEvent);
        } catch (IncorrectInformationException e) {
            returnEvent = new Event("MerchantReportNotSent", new Object[] {e.getMessage()});
            queue.publish(returnEvent);
        }
    }
    public void handleReportCustomer(Event event){
        var s = event.getArgument(0, String.class);
        Event returnEvent;
        try {
            returnEvent = new Event("CustomerReportSent", new Object[] {register.getReportForCustomer(s)});
            queue.publish(returnEvent);
        } catch (IncorrectInformationException e) {
            returnEvent = new Event("CustomerReportNotSent", new Object[] {e.getMessage()});
            queue.publish(returnEvent);
        }
    }
}
