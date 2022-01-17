package report.service.adapter;

import messaging.Event;
import messaging.MessageQueue;
import report.service.DTO.ReportRequest;
import report.service.exception.IncorrectInformationException;
import report.service.ReportService;
import report.service.port.IReportService;
import report.service.storage.ReportRepository;

public class ReportController {

    MessageQueue queue;
    IReportService service = new ReportService(new ReportRepository());

    public ReportController(MessageQueue q) {
        queue = q;
        queue.addHandler("CustomerRegisteredSuccessfully", this::handleCreateUserInReportRegister);
        queue.addHandler("ReportCreationRequest", this::handleCreateReport);
        queue.addHandler("ReportManager", this::handleReportManager);
        queue.addHandler("ReportsRequest", this::handleReportRequest);
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

    public void handleReportManager(Event event){
        Event returnEvent;
        returnEvent = new Event("AllReportsRequestedSucceeded", new Object[] {service.getManagerReports()});
        queue.publish(returnEvent);
    }
    public void handleReportRequest(Event event){

        var userID = event.getArgument(0, String.class);
        Event returnEvent;
        try {
            returnEvent = new Event("ReportsSent", new Object[] {service.getReportsByID(userID)});
            queue.publish(returnEvent);
        } catch (IncorrectInformationException e) {
            returnEvent = new Event("ReportsNotSent", new Object[] {e.getMessage()});
            queue.publish(returnEvent);
        }
    }
}
