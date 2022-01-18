package report.service;

import report.service.DTO.*;
import report.service.exception.IncorrectInformationException;
import report.service.port.IReportRepository;
import report.service.port.IReportService;

import java.util.ArrayList;

public class ReportService implements IReportService {
    private final IReportRepository repository;

    public ReportService(IReportRepository repository) {
        this.repository = repository;
    }

    public void createUser(String userid) {
        repository.createReportList(userid);
    }

    public Report createReport(ReportRequest request) throws IncorrectInformationException {
        CustomerReport customerReport = new CustomerReport();
        MerchantReport merchantReport = new MerchantReport();

        customerReport.setPaymentID(request.getPaymentID());
        customerReport.setCustomerID(request.getCustomerID());
        customerReport.setMerchantID(request.getMerchantID());
        customerReport.setTokenID(request.getTokenID());
        customerReport.setBankID(request.getCustomerBankID());
        customerReport.setAmount(request.getAmount());

        merchantReport.setPaymentID(request.getPaymentID());
        merchantReport.setMerchantID(request.getMerchantID());
        merchantReport.setTokenID(request.getTokenID());
        merchantReport.setBankID(request.getMerchantBankID());
        merchantReport.setAmount(request.getAmount());

        try {
            repository.addReport(request.getCustomerID(), customerReport);
        } catch (IncorrectInformationException ignored) {
            throw new IncorrectInformationException("Customer doesn't exist");
        }
        try {
            repository.addReport(request.getMerchantID(), merchantReport);
        } catch (IncorrectInformationException ignored) {
            throw new IncorrectInformationException("merchant doesn't exist");
        }
        return merchantReport;
    }


    public ReportList getManagerReports() {
        ArrayList<ReportList> temp = repository.getReports();
        ReportList results = new ReportList();

        for (ReportList reportList : temp ) {
            results.getReportList().addAll(reportList.getReportList());
        }
        return results;
    }

    public ReportList getReportsByID(String userID) throws IncorrectInformationException {
        try{
            return repository.getReportsByUser(userID);
        }catch (IncorrectInformationException e){
            throw new IncorrectInformationException("Merchant doesn't exist");
        }
    }
}
