package MerchantAPI;

import DTO.UserDTO;

import java.util.HashMap;

public class MerchantAPI {

    public String VerifyToken(String token){
        return "";
    
    HashMap<String, UserDTO> merchantList = new HashMap<>();
    public String registerMerchant(UserDTO user){
        String registerID = "din merchant mor";
        merchantList.put(registerID, user);
        return registerID;
    }
    
    public String VerifyToken(){
        return "invalid";
    }
    public HashMap<String, UserDTO> getMerchantList() {
        return merchantList;
    }
}
