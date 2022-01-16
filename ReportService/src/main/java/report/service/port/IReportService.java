package report.service.port;

import report.service.DTO.Report;
import report.service.DTO.ReportList;
import report.service.DTO.ReportRequest;
import report.service.exception.IncorrectInformationException;

import java.util.ArrayList;

public interface IReportService {
    public void createUser(String userID);

    public Report createReport(ReportRequest request) throws IncorrectInformationException;


    public ArrayList<Report> getManagerReports();

    public ReportList getReportsByID(String merchantID) throws IncorrectInformationException;

}
