package report.service.DTO;

import java.io.Serializable;
import java.math.BigDecimal;

public class ReportRequest implements Serializable {

    private static final long serialVersionUID = 9023222981284806610L;

    String paymentID;
    String customerID;
    String merchantID;
    String tokenID;
    String customerbankID;
    String merchantbankID;
    BigDecimal amount;

    public ReportRequest(String paymentID, String customerID, String merchantID, String tokenID, String customerbankID, String merchantbankID, BigDecimal amount) {
        this.paymentID = paymentID;
        this.customerID = customerID;
        this.merchantID = merchantID;
        this.tokenID = tokenID;
        this.customerbankID = customerbankID;
        this.merchantbankID = merchantbankID;
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

    public String getCustomerbankID() {
        return customerbankID;
    }

    public void setCustomerbankID(String customerbankID) {
        this.customerbankID = customerbankID;
    }

    public String getMerchantbankID() {
        return merchantbankID;
    }

    public void setMerchantbankID(String merchantbankID) {
        this.merchantbankID = merchantbankID;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
