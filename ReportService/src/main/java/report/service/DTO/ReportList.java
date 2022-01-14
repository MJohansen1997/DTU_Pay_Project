package report.service.DTO;

import java.io.Serializable;
import java.util.ArrayList;

public class ReportList implements Serializable {
    private static final long serialVersionUID = 9023222981284806610L;

    private ArrayList<Report> Reports;

    public ReportList() {
        this.Reports = new ArrayList<>();
    }

    public ArrayList<Report> getReportList() {
        return Reports;
    }

    public void setReportList(ArrayList<Report> Reports) {
        this.Reports = Reports;
    }
}
