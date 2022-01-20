package report.service;

import report.service.DTO.*;
import report.service.exception.IncorrectInformationException;
import report.service.port.IReportRepository;
import report.service.port.IReportService;

import java.util.ArrayList;

public class ReportService implements IReportService {
    int paymentIDIncrementer = 0;
    private final IReportRepository repository;

    public ReportService(IReportRepository repository) {
        this.repository = repository;
    }

    //create the user in the report repository
    public void createUser(String userid) {
        repository.createReportList(userid);
    }

    public Report createReport(ReportRequest request) throws IncorrectInformationException {
        CustomerReport customerReport = new CustomerReport();
        MerchantReport merchantReport = new MerchantReport();

        //actual make the customerReport
        customerReport.setPaymentID(String.valueOf(paymentIDIncrementer));
        customerReport.setCustomerID(request.getCustomerID());
        customerReport.setMerchantID(request.getMerchantID());
        customerReport.setTokenID(request.getTokenID());
        customerReport.setBankID(request.getCustomerBankID());
        customerReport.setAmount(request.getAmount());

        //actual make the merchantReport
        merchantReport.setPaymentID(String.valueOf(paymentIDIncrementer++));
        merchantReport.setMerchantID(request.getMerchantID());
        merchantReport.setTokenID(request.getTokenID());
        merchantReport.setBankID(request.getMerchantBankID());
        merchantReport.setAmount(request.getAmount());

        //adds the two reports to the report repository
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


    //gets all the reports from report repository
    public ReportList getManagerReports() {
        ArrayList<ReportList> temp = repository.getReports();
        ReportList results = new ReportList();

        for (ReportList reportList : temp ) {
            results.getReportList().addAll(reportList.getReportList());
        }
        return results;
    }

    //get reports based om the customer og the merchants ID
    public ReportList getReportsByID(String userID) throws IncorrectInformationException {
        try{
            return repository.getReportsByUser(userID);
        }catch (IncorrectInformationException e){
            throw new IncorrectInformationException("Merchant doesn't exist");
        }
    }
}
