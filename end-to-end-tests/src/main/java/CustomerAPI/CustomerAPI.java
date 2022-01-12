package CustomerAPI;

import DTO.UserDTO;

import java.util.HashMap;

public class CustomerAPI {
    HashMap<String, UserDTO> customerList = new HashMap<>();
    public String registerCustomer(UserDTO user){
        String registerID = "din customer mor";
        customerList.put(registerID, user);
        return registerID;
    }

    public HashMap<String, UserDTO> getCustomerList() {
        return customerList;
    }
}
