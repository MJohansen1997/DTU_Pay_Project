package payment.service.DTO;

import java.io.Serializable;
import java.math.BigDecimal;

public class Payment implements Serializable {
    private static final long serialVersionUID = 9023222981284806610L;
    public String debitor;
    public String creditor;
    public BigDecimal amount;
    public String description;
    public String errorMessage;

    public Payment(String debitor, String creditor, BigDecimal amount, String description, String errorMessage) {
        this.debitor = debitor;
        this.creditor = creditor;
        this.amount = amount;
        this.description = description;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() { return errorMessage; }

    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }

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
}
