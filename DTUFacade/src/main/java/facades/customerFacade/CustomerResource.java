package facades.customerFacade;

import studentregistration.service.Student;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/customer")
public class CustomerResource {
    @GET
    public String helloWorld() {
        return "hello world";
    }
}
