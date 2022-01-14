package facades.DTO;

import java.io.Serializable;
import java.math.BigDecimal;

public class Payment implements Serializable {
    private static final long serialVersionUID = 9023222981284806610L;
    public String debitor;
    public String creditor;
    public BigDecimal amount;
    public String description;
    public String token;

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
}
