package facades.merchantFacade;

import facades.DTO.Account;
import facades.DTO.Payment;
import facades.DTO.RegistrationDTO;
import facades.DTO.ReportRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;

@Path("/merchant")
public class MerchantResource {
    @POST
    @Path("register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerMerchant(RegistrationDTO RegInfo) {
        String id;
        try {
            MerchantFacade CF = new MerchantFacadeFactory().getFacade();
            id = CF.registerMerchant(RegInfo);
            /* REWRITE TO CUSTOM EXCEPTION */
        } catch (Exception e) {
            return Response.status(404).entity(e.getMessage()).build();
        }
        return Response.status(200).entity(id).build();
    }

    @Path("payment")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response payment(Payment payment) {
        String tokenID = payment.getToken();
        MerchantFacade CF = new MerchantFacadeFactory().getFacade();
        String customer_userId = CF.consumeToken(tokenID);
        //if the token consumed returns a user and that user is connected to the payment
        if(customer_userId!=null && customer_userId.length()>0 && !customer_userId.equalsIgnoreCase("Invalid token, no such token")){
            String userBankAccountID = payment.debitor;
            Account account = CF.getSpecificUser(customer_userId);
            if(!userBankAccountID.equalsIgnoreCase(account.getBankID())){
                return Response.status(401).entity("Not authorized").build();
            }
        }else{
            return Response.status(404).entity("Invalid token, no such token").build();
        }
        //payment.setDebitor(customer_userId);
        Payment p = CF.paymentMerchant(payment);
        if(p.getErrorMessage()!=null){
            return Response.status(404).entity(p.getErrorMessage()).build();
        }else{
            //Create a report
            String customerID = customer_userId;
            String merchantID = p.getMerchantId();
            String tokenId = p.getToken();
            String customerBankID = p.getDebitor();
            String merchantBankID = p.getCreditor();
            BigDecimal amount = p.getAmount();

            //This method creates the report
            //CF.createReport(new ReportRequest("",customerID,merchantID,tokenId,customerBankID,merchantBankID,amount ));

            return Response.status(200).entity(payment).build();
        }
    }
}
