package report.service.DTO;

import java.io.Serializable;
import java.math.BigDecimal;

public abstract class Report implements Serializable {

    private static final long serialVersionUID = 9023222981284806610L;

    String paymentID;
    String merchantID;
    String tokenID;
    String bankID;
    BigDecimal amount;

    public Report(String paymentID, String merchantID, String tokenID, String bankID, BigDecimal amount) {
        this.paymentID = paymentID;
        this.merchantID = merchantID;
        this.tokenID = tokenID;
        this.bankID = bankID;
        this.amount = amount;
    }

    public Report() {
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
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

    public String getBankID() {
        return bankID;
    }

    public void setBankID(String bankID) {
        this.bankID = bankID;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
