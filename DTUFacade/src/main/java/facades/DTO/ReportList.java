package facades.DTO;

import java.io.Serializable;
import java.util.ArrayList;

public class ReportList implements Serializable {
    private static final long serialVersionUID = 9023222981284806610L;

    private ArrayList<report.service.DTO.Report> reports;

    public ReportList() {
        this.reports = new ArrayList<>();
    }

    public ArrayList<report.service.DTO.Report> getReportList() {
        return reports;
    }

    public void setReportList(ArrayList<report.service.DTO.Report> Reports) {
        this.reports = Reports;
    }

    public report.service.DTO.Report containsPayment(String ID) {
        for (report.service.DTO.Report report : reports) {
            if (report.getPaymentID().equals(ID))
                return report;
        }
        return null;
    }
}
