package CustomerAPI;

import DTO.CustomerReportList;
import DTO.RegistrationDTO;
import DTO.Report.ReportList;
import DTO.TokenList;
import dtu.ws.fastmoney.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.*;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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

    public String requestTokens(String userID){
        WebTarget target = client.target("http://localhost:8080/customer/tokens");
        try {
            tokens = target.request(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .post(Entity.json(userID), TokenList.class).getTokens();
            return "Tokens received";
        }catch (NotFoundException exception) {
            //How does we handle exceptions here? HTTP or Custom exceptions?
            return "Tokens not received";
        }
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

    public String registerCustomer(UserDTO user) {
        WebTarget target = client.target("http://localhost:8080/customer/account");
        String result;
        try {
            result = target.request(MediaType.APPLICATION_JSON)
                    .accept(MediaType.TEXT_PLAIN_TYPE)
                    .post(Entity.json(user), String.class);

            String registerID = result;
            customerList.put(result, user);
            setDTUPayID(registerID);
            return registerID;

        }catch (Exception exception) {
            return "404";
        }
    }

    CustomerReportList reports = new CustomerReportList();

    public CustomerReportList requestCustomerReports(String customerID) {
        WebTarget target = client.target("http://localhost:8080/customer/reports/" + customerID);
        try {
            reports = target.request()
                    .accept(MediaType.APPLICATION_JSON)
                    .get(CustomerReportList.class);
            return reports;
        } catch (NotFoundException exception) {
            //How does we handle exceptions here? HTTP or Custom exceptions?
            throw new NotFoundException("Reports doesn't exist");
        }
    }

    public HashMap<String, UserDTO> getCustomerList() {
        return customerList;
    }

    public String getDTUPayID() {
        return DTUPayID;
    }

    public void setDTUPayID(String DTUPayID) {
        this.DTUPayID = DTUPayID;
    }
}
