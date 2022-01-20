package account.service.DTO;

import lombok.*;

//The aggregate root object we receive from our Facade
@AllArgsConstructor
public @Data class Account {
    String firstName;
    String lastName;
    String cpr;
    String bankID;
    String roleID;
}
