package MerchantAPI;

import DTO.UserDTO;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;

public class MerchantAPI {
    Client client = ClientBuilder.newClient();
    public String VerifyToken(String token) {
        return "";
    }

    HashMap<String, UserDTO> merchantList = new HashMap<>();

    public String registerMerchant(UserDTO user){
        WebTarget target = client.target("http://localhost:8080/merchant/register");
        String result;
        try {
            result = target.request(MediaType.APPLICATION_JSON)
                    .accept(MediaType.TEXT_PLAIN_TYPE)
                    .post(Entity.json(user), String.class);

            merchantList.put(result, user);
            return result;
        }catch (Exception exception) {
            return "404";
        }
    }

    public HashMap<String, UserDTO> getMerchantList() {
        return merchantList;
    }
}
