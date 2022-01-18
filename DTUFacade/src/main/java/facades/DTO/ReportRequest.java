package facades.DTO;

import java.math.BigDecimal;

public class ReportRequest {
    private static final long serialVersionUID = 9023222981284806610L;

    String paymentID;
    String customerID;
    String merchantID;
    String tokenID;
    String customerBankID;
    String merchantBankID;
    BigDecimal amount;

    public ReportRequest(String paymentID, String customerID, String merchantID, String tokenID, String customerBankID, String merchantbankID, BigDecimal amount) {
        this.paymentID = paymentID;
        this.customerID = customerID;
        this.merchantID = merchantID;
        this.tokenID = tokenID;
        this.customerBankID = customerBankID;
        this.merchantBankID = merchantbankID;
        this.amount = amount;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getMerchantID() {
        return merchantID;
    }

    public void setMerchantID(String merchantID) {
        this.merchantID = merchantID;
    }

    public String getTokenID() {
        return tokenID;
    }

    public void setTokenID(String tokenID) {
        this.tokenID = tokenID;
    }

    public String getCustomerBankID() {
        return customerBankID;
    }

    public void setCustomerBankID(String customerBankID) {
        this.customerBankID = customerBankID;
    }

    public String getMerchantBankID() {
        return merchantBankID;
    }

    public void setMerchantBankID(String merchantBankID) {
        this.merchantBankID = merchantBankID;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}

