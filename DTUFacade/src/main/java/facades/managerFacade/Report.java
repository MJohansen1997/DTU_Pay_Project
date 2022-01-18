package facades.managerFacade;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement // Needed for XML serialization and deserialization
//@Data // Automatic getter and setters and equals etc
@NoArgsConstructor // Needed for JSON deserialization and XML serialization and deserialization
@AllArgsConstructor
public @Data
class Report {
    String paymentID;
    String customerID;
    String merchantID;
    String tokenID;
    String customerBankID;
    String merchantBankID;
    BigDecimal amount;
}
