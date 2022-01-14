package report.service.DTO;

import java.io.Serializable;
import java.math.BigDecimal;

public class CustomerReport extends Report implements Serializable {
    private static final long serialVersionUID = 9023222981284806610L;

    String customerID;

    public CustomerReport(String customerID, String merchantID, String tokenID, String bankID, BigDecimal amount) {
        super(merchantID, tokenID, bankID, amount);
        this.customerID = customerID;
    }
    public CustomerReport(){super();}

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

}
