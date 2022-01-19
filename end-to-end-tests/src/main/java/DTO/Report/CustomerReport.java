package DTO.Report;

import java.io.Serializable;
import java.math.BigDecimal;

public class CustomerReport extends Report implements Serializable {
    private static final long serialVersionUID = 9023222981284806610L;

    String customerID;

    public CustomerReport(String paymentID, String customerID, String merchantID, String tokenID, String bankID, BigDecimal amount) {
        super(paymentID, merchantID, tokenID, bankID, amount);
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
