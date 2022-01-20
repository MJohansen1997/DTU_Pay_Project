package facades.customerFacade;

import facades.DTO.CustomerReport;
import facades.DTO.CustomerReportList;
import facades.DTO.RegistrationDTO;
import facades.DTO.ReportList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

//Default rest path to access customer
@Path("/customer")
public class CustomerResource{

    //Endpoint for registering a customer
    @POST
    @Path("account")
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
//        return Response.status(200).entity("User registered with id: " + id).build();
        return Response.status(200).entity(id).build();
    }

    //Endpoint for requesting tokens
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

    //Endpoint for requesting a report list based of customerID
    @Path("reports/{customerID}")
    @GET
    @Produces("application/json")
    public Response getReports(@PathParam("customerID") String userID) {
        CustomerFacade CF = new CustomerFacadeFactory().getFacade();
        CustomerReportList reportList = CF.reportListRequest(userID);

        return Response.status(200).entity(reportList).build();
    }
}
