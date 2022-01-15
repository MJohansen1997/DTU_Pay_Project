package facades.customerFacade;

import facades.DTO.RegistrationDTO;
import facades.DTO.TokenList;
import facades.ICustomer;
import studentregistration.service.Student;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/customer")
public class CustomerResource{

    @POST
    @Path("register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerCustomer(RegistrationDTO RegInfo) {
        String id;
        try {
            CustomerFacade CF = new CustomerFacadeFactory().getFacade();
            id = CF.registerCustomer(RegInfo);

        /* REWRITE TO CUSTOM EXCEPTION */
        } catch (Exception e ) {
            return Response.status(404).entity(e.getMessage()).build();
        }
        return Response.status(200).entity("User registered with id: " + id).build();
    }

    @POST
    @Path("tokens")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response requestTokens(String userID) {
        try {
            CustomerFacade CF = new CustomerFacadeFactory().getFacade();
            return Response.status(Response.Status.ACCEPTED).entity(CF.requestTokens(userID)).build();
        }
        catch (Exception e) {
            return Response.status(404).entity(e.getMessage()).build();
        }
    }

}
