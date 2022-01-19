package MerchantAPI;

import DTO.Report.ReportList;
import DTO.Payment;
import DTO.TokenList;
import DTO.UserDTO;
import dtu.ws.fastmoney.*;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.util.HashMap;

public class MerchantAPI {
    Client client = ClientBuilder.newClient();
    String bankID;
    HashMap<String, UserDTO> merchantList = new HashMap<>();
    BankService bank = new BankServiceService().getBankServicePort();


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
    public void createAccount(String firstName, String lastName, String CPR, BigDecimal balance) throws BankServiceException_Exception {
//        bank.retireAccount("a7110fec-943c-4343-8145-4cf737f182dd");
//        for(AccountInfo accountInfo : bank.getAccounts()) {
//            System.out.println(accountInfo.getAccountId() + " : " + accountInfo.getUser().getLastName());
//        }
        ObjectFactory objectFactory = new ObjectFactory();
        User user = objectFactory.createUser();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setCprNumber(CPR);
        bankID = bank.createAccountWithBalance(user,balance);
        }

    public HashMap<String, UserDTO> getMerchantList() {
        return merchantList;
    }

    ReportList reports = new ReportList();

    //Doesnt take any id yet
    public ReportList requestMerchantReports(String merchantID) {
        WebTarget target = client.target("http://localhost:8080/merchant/reports/" + merchantID);
        try {
            reports = target.request()
                    .accept(MediaType.APPLICATION_JSON)
                    .get(ReportList.class);
            return reports;
        } catch (NotFoundException exception) {
            //How does we handle exceptions here? HTTP or Custom exceptions?
            throw new NotFoundException("Reports doesn't exist");
        }
    }

    public Payment payment(Payment payment) {
        
        WebTarget target = client.target("http://localhost:8080/merchant/payment");

        Payment result = target.request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.json(payment), Payment.class);
        return result;
        
    }
}
