package report.service;

import report.service.DTO.*;

import java.util.ArrayList;
import java.util.HashMap;

public class ReportRegister {
    MerchantReport merchantReport;
    HashMap<String, ReportList> customerList = new HashMap<>();
    HashMap<String, ReportList> merchantList = new HashMap<>();

    public String insertUser(String userid) {
        if (userid.charAt(0) == 'c')
            customerList.put(userid, new ReportList());
        else
            merchantList.put(userid, new ReportList());
        return "Successful";
    }

    public String createReport(ReportRequest request) throws IncorrectInformationException {
        CustomerReport customerReport = new CustomerReport();

        customerReport.setPaymentID(request.getPaymentID());
        customerReport.setCustomerID(request.getCustomerID());
        customerReport.setMerchantID(request.getMerchantID());
        customerReport.setTokenID(request.getTokenID());
        customerReport.setBankID(request.getCustomerbankID());
        customerReport.setAmount(request.getAmount());

        merchantReport.setPaymentID(request.getPaymentID());
        merchantReport.setMerchantID(request.getMerchantID());
        merchantReport.setTokenID(request.getTokenID());
        merchantReport.setBankID(request.getMerchantbankID());
        merchantReport.setAmount(request.getAmount());

        try {
            customerList.get(request.getCustomerID()).getReportList().add(customerReport);
        } catch (NullPointerException e) {
            throw new IncorrectInformationException("Customer doesn't exist");
        }

        try {
            merchantList.get(request.getMerchantID()).getReportList().add(merchantReport);
        } catch (NullPointerException e) {
            throw new IncorrectInformationException("Customer doesn't exist");
        }
        return "Sucessful";
    }


    public ArrayList<Report> getReportsForManager() {
        ArrayList<Report> list = new ArrayList<>();

        for (String key : customerList.keySet()) {
            list.addAll(customerList.get(key).getReportList());
        }
        for (String key : merchantList.keySet()) {
            list.addAll(merchantList.get(key).getReportList());
        }
        return list;
    }

    public ReportList getReportForMerchant(String merchantID) throws IncorrectInformationException {
        try{
            return merchantList.get(merchantID);
        }catch (NullPointerException e){
            throw new IncorrectInformationException("Merchant doesn't exist");
        }
    }

    public ReportList getReportForCustomer(String customerID) throws IncorrectInformationException {
        try{
            return customerList.get(customerID);
        }catch (NullPointerException e){
            throw new IncorrectInformationException("Customer doesn't exist");
        }
    }

}
