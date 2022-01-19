package facades.DTO;

import report.service.DTO.Report;

import java.io.Serializable;
import java.math.BigDecimal;

public class MerchantReport extends Report implements Serializable {
    private static final long serialVersionUID = 9023222981284806610L;

    public MerchantReport(String paymentID, String merchantID, String tokenID, String bankID, BigDecimal amount) {
        super(paymentID, merchantID, tokenID, bankID, amount);
    }
    public MerchantReport(){super();}
}
