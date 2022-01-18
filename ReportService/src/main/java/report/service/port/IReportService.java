package report.service.port;

import report.service.DTO.Report;
import report.service.DTO.ReportList;
import report.service.DTO.ReportRequest;
import report.service.exception.IncorrectInformationException;

public interface IReportService {
    public void createUser(String userID);

    public Report createReport(ReportRequest request) throws IncorrectInformationException;


    public ReportList getManagerReports();

    public ReportList getReportsByID(String merchantID) throws IncorrectInformationException;

}
