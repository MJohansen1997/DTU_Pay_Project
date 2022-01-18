package facades.managerFacade;

import dtu.ws.fastmoney.AccountInfo;
import messaging.Event;
import messaging.MessageQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ManagerFacade {
    private MessageQueue queue;
    private CompletableFuture<String> future;
    private CompletableFuture<ReportList> reportRequested;

    public ManagerFacade(MessageQueue q) {
        queue = q;
        queue.addHandler("ReportsRequestedSucceeded", this::succesfulReportRequested);
    }


    private void succesfulReportRequested(Event e) {
        var report = e.getArgument(0, ReportList.class);
        reportRequested.complete(report);
    }

    public ReportList reportListRecived() {
        reportRequested = new CompletableFuture<>();
        Event event = new Event("ReportManager", new Object[] {  });
        queue.publish(event);
        return reportRequested.join();
    }

//    public ArrayList<AccountInfo> getAccountsManager() {
//        reportFuture = new CompletableFuture<>();
//        Event tempE = new Event("MerchantGetAccounts",  new Object[] { });
//        queue.publish(tempE);
//        return reportFuture.join();
//    }

}
