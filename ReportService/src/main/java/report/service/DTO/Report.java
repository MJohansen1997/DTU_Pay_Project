package report.service.DTO;

import java.math.BigDecimal;

public abstract class Report {

    String merchantID;
    String tokenID;
    String bankID;
    BigDecimal amount;

    public Report(String merchantID, String tokenID, String bankID, BigDecimal amount) {
        this.merchantID = merchantID;
        this.tokenID = tokenID;
        this.bankID = bankID;
        this.amount = amount;
    }
    public Report(){}

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
