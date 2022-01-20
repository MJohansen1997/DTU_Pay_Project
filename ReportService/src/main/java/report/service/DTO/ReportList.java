package report.service.DTO;

import java.io.Serializable;
import java.util.ArrayList;

public class ReportList implements Serializable {
    private static final long serialVersionUID = 9023222981284806610L;

    private ArrayList<Report> reports;

    //make a datatype ReportList as an arraylist of Reports
    public ReportList() {
        this.reports = new ArrayList<>();
    }

    public ArrayList<Report> getReportList() {
        return reports;
    }

    public void setReportList(ArrayList<Report> Reports) {
        this.reports = Reports;
    }

    public Report containsPayment(String ID) {
        for (Report report : reports) {
            if (report.paymentID.equals(ID))
                return report;
        }
        return null;
    }
}
