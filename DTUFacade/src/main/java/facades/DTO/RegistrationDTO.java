package facades.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement // Needed for XML serialization and deserialization
//@Data // Automatic getter and setters and equals etc
@NoArgsConstructor // Needed for JSON deserialization and XML serialization and deserialization
@AllArgsConstructor
public class RegistrationDTO implements Serializable {
    private static final long serialVersionUID = 9023222981284806610L;
    @Getter @Setter String firstName;
    @Getter @Setter String lastName;
    @Getter @Setter String cpr;
    @Getter @Setter String bankID;
}
