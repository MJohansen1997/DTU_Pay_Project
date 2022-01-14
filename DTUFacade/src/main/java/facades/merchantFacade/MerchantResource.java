package facades.merchantFacade;

import facades.DTO.Payment;
import facades.DTO.RegistrationDTO;
import facades.customerFacade.CustomerFacade;
import facades.customerFacade.CustomerFacadeFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/merchant")
public class MerchantResource {
    @POST
    @Path("register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerCustomer(RegistrationDTO RegInfo) {
        String id;
        try {
            MerchantFacade CF = new MerchantFacadeFactory().getFacade();
            id = CF.registerMerchant(RegInfo);
            /* REWRITE TO CUSTOM EXCEPTION */
        } catch (Exception e ) {
            return Response.status(404).entity(e.getMessage()).build();
        }
        return Response.status(200).entity("User registered with id: " + id).build();
    }

    @Path("/payment")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Payment payment(Payment payment) {
        String tokenID = payment.getToken();
        MerchantFacade CF = new MerchantFacadeFactory().getFacade();
        //maybe do some checks here :-)
        String userID = CF.consumeToken(tokenID);
        payment.setDebitor(userID);
        return CF.paymentMerchant(payment);
    }
}
