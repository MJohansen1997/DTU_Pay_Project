package account.service.DTO;

import lombok.*;

@AllArgsConstructor
public @Data class Account {
    String firstName;
    String lastName;
    String cpr;
    String bankID;
    String roleID;
}
