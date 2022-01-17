package facades.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


public enum UserType {
    C(UserType.CUSTOMER),
    M(UserType.MERCHANT);

    public static final String CUSTOMER = "Customer";
    public static final String MERCHANT = "Merchant";

    UserType(String m) {

    }
}
