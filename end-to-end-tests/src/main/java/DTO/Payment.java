package DTO;

import java.io.Serializable;
import java.math.BigDecimal;

public class Payment implements Serializable {
    private static final long serialVersionUID = 9023222981284806610L;
    public String debitor;
    public String creditor;
    public String merchantId;
    public BigDecimal amount;
    public String description;
    public String token;
    public String errorMessage;

    public Payment(String debitor, String creditor, String merchantId, BigDecimal amount, String description, String token) {
        this.debitor = debitor;
        this.creditor = creditor;
        this.merchantId = merchantId;
        this.amount = amount;
        this.description = description;
        this.token = token;
    }

    public String getErrorMessage() { return errorMessage; }

    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDebitor() {
        return debitor;
    }

    public void setDebitor(String debitor) {
        this.debitor = debitor;
    }

    public String getCreditor() {
        return creditor;
    }

    public void setCreditor(String creditor) {
        this.creditor = creditor;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMerchantId() { return merchantId; }

    public void setMerchantId(String merchantId) { this.merchantId = merchantId; }
}
