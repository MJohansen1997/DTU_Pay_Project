package facades.managerFacade;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/manager")
public class ManagerResource {
    @GET
    public String helloWorld() {
        return "hello world";
    }
}
