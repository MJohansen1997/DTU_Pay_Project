package DTO.Report;

import java.io.Serializable;
import java.util.ArrayList;

public class ReportList implements Serializable {
    private static final long serialVersionUID = 9023222981284806610L;

    private ArrayList<ReportManager> reportManagers;

    public ReportList() {
        this.reportManagers = new ArrayList<>();
    }

    public ArrayList<ReportManager> getReportList() {
        return reportManagers;
    }

    public void setReportList(ArrayList<ReportManager> reportManagers) {
        this.reportManagers = reportManagers;
    }
}
