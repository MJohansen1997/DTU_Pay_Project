package facades.DTO;

import java.io.Serializable;
import java.util.ArrayList;

public class MerchantReportList implements Serializable {
    private static final long serialVersionUID = 9023222981284806610L;

    private ArrayList<MerchantReport> reports;

    public MerchantReportList() {
        this.reports = new ArrayList<>();
    }

    public ArrayList<MerchantReport> getReportList() {
        return reports;
    }

    public void setReportList(ArrayList<MerchantReport> Reports) {
        this.reports = Reports;
    }

    public MerchantReport containsPayment(String ID) {
        for (MerchantReport report : reports) {
            if (report.paymentID.equals(ID))
                return report;
        }
        return null;
    }
}
