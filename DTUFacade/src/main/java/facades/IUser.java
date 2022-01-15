package facades;

import javax.ws.rs.*;

import javax.ws.rs.core.Response;

public interface IUser {
    
    @POST
    Response register();
    
    @GET
    Response generateReport();
}
