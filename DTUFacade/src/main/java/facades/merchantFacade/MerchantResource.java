package facades.merchantFacade;

import facades.DTO.Payment;
import facades.DTO.RegistrationDTO;
import facades.managerFacade.ManagerFacade;
import facades.managerFacade.ManagerFacadeFactory;
import facades.managerFacade.ReportList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
        //String userID = CF.consumeToken(tokenID);
        //payment.setDebitor(userID);
        Payment p = CF.paymentMerchant(payment);
        if(p.getErrorMessage()!=null){
            return Response.status(404).entity(p.getErrorMessage()).build();
        }else{
            return Response.status(200).entity(payment).build();
        }
    }
    @Path("report")
    @GET
    @Produces("application/json")
    public Response getReports(String paymentID) {
        MerchantFacade MF = new MerchantFacadeFactory().getFacade();
        ReportList reportList = MF.reportListRecived(paymentID);

        return Response.status(200).entity(reportList).build();
    }
}
