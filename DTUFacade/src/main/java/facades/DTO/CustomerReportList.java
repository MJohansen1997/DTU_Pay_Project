package facades.DTO;

import java.io.Serializable;
import java.util.ArrayList;

public class CustomerReportList implements Serializable {
    private static final long serialVersionUID = 9023222981284806610L;

    private ArrayList<CustomerReport> reports;

    public CustomerReportList() {
        this.reports = new ArrayList<>();
    }

    public ArrayList<CustomerReport> getReportList() {
        return reports;
    }

    public void setReportList(ArrayList<CustomerReport> Reports) {
        this.reports = Reports;
    }

    public CustomerReport containsPayment(String ID) {
        for (CustomerReport report : reports) {
            if (report.paymentID.equals(ID))
                return report;
        }
        return null;
    }
}
