package report.service.storage;

import report.service.DTO.Report;
import report.service.DTO.ReportList;
import report.service.exception.IncorrectInformationException;
import report.service.port.IReportRepository;

import java.util.ArrayList;
import java.util.HashMap;

public class ReportRepository implements IReportRepository {
    HashMap<String, ReportList> reportRepository = new HashMap<>();

    @Override
    public ArrayList<ReportList> getReports() {
        ArrayList<ReportList> temp = new ArrayList<>();
        for ( String stringID : reportRepository.keySet()) {
            temp.add(reportRepository.get(stringID));
        }
        return temp;
    }

    @Override
    public ReportList getReportsByUser(String userID) throws IncorrectInformationException {
        ReportList temp = reportRepository.get(userID);
        if (temp == null)
            throw new IncorrectInformationException("User not found");
        return temp;
    }

    @Override
    public Report getReportByPaymentID(String paymentID) throws IncorrectInformationException {
        Report temp;
        for (String stringID : reportRepository.keySet()) {
            temp = reportRepository.get(stringID).containsPayment(paymentID);
            if (temp != null)
                return temp;
        }
        throw new IncorrectInformationException("paymentID not found");
    }

    @Override
    public ReportList createReportList(String userID) {
        ReportList temp = new ReportList();
        reportRepository.put(userID, temp);
        return temp;
    }

    @Override
    public ReportList addReport(String userID, Report newReport) throws IncorrectInformationException {
        ReportList temp = reportRepository.get(userID);
        if (temp == null)
            throw new IncorrectInformationException("User not found");
        temp.getReportList().add(newReport);
        return temp;
    }
}
