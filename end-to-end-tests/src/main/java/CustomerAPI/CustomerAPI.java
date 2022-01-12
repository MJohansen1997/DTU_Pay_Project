package CustomerAPI;

import dtu.ws.fastmoney.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import javax.ws.rs.client.*;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import DTO.UserDTO;

import java.util.HashMap;

public class CustomerAPI {
    private ArrayList<String> tokens = new ArrayList<>();
    private ArrayList<String> used = new ArrayList<>();
    HashMap<String, UserDTO> customerList = new HashMap<>();
    Client client = ClientBuilder.newClient();
    String bankID;
    String DTUPayID;

    BankService bank = new BankServiceService().getBankServicePort();

    public String requestTokens(){
        WebTarget target = client.target("http://localhost:8080/payment");
        ArrayList<String> result;
        try {
            result = target.request(MediaType.APPLICATION_JSON)
                    .accept(MediaType.TEXT_PLAIN_TYPE)
                    .get(new GenericType<>() {});
            tokens.addAll(result);
            return "success";
        }catch (Exception exception) {
            return "You still have at least 2 unused Tokens";
        }
    }

    public void createAccount(String firstName, String lastName, String CPR, BigDecimal balance) throws BankServiceException_Exception {
        ObjectFactory objectFactory = new ObjectFactory();
        User user = objectFactory.createUser();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setCprNumber(CPR);
        bankID = bank.createAccountWithBalance(user,balance);
    }

    public void Register(){
        WebTarget target = client.target("http://localhost:8080/account");
//        String result = target.request(MediaType.APPLICATION_JSON)
//                .accept(MediaType.TEXT_PLAIN_TYPE)
//                .post(Entity.json(), String.class);
        DTUPayID = "";
    }

    public void retireAccount() throws BankServiceException_Exception {
        bank.retireAccount(bankID);
    }

    public ArrayList<String> getTokens() {
        return tokens;
    }

    public void setTokens(ArrayList<String> tokens) {
        this.tokens = tokens;
    }

    public ArrayList<String> getUsed() {
        return used;
    }

    public void setUsed(ArrayList<String> used) {
        this.used = used;
    }
    public String registerCustomer(UserDTO user){
        String registerID = "din customer mor";
        customerList.put(registerID, user);
        return registerID;
    }

    public HashMap<String, UserDTO> getCustomerList() {
        return customerList;
    }
}
