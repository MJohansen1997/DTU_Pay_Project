package DTO.Report;

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
class ReportMerchant extends Report {
    String paymentID;
    String merchantID;
    String tokenID;
    String bankID;
    BigDecimal amount;
}
