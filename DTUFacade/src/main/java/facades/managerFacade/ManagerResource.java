package facades.managerFacade;

//import dtu.ws.fastmoney.AccountInfo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/manager")
public class ManagerResource {

    @Path("accounts")
    @GET
    @Produces("application/json")
    public Response getAccounts() {
        ManagerFacade managerFacade = new ManagerFacadeFactory().getFacade();
//        ArrayList<AccountInfo> accounts = managerFacade.getAccountsManager();
//        return Response.status(200).entity(accounts).build();
        return Response.status(200).entity(null).build();
    }
}
