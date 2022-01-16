package report.service.port;

import report.service.DTO.Report;
import report.service.DTO.ReportList;
import report.service.exception.IncorrectInformationException;

import java.util.ArrayList;

public interface IReportRepository {
    ArrayList<ReportList> getReports();
    ReportList getReportsByUser(String userID) throws IncorrectInformationException;
    Report getReportByPaymentID(String paymentID) throws IncorrectInformationException;
    ReportList createReportList(String userID);
    ReportList addReport(String userID, Report newReport) throws IncorrectInformationException;
}
