package facades.managerFacade;

import dtu.ws.fastmoney.AccountInfo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/manager")
public class ManagerResource {

    @Path("reports")
    @GET
    @Produces("application/json")
    public Response getReports() {
        ManagerFacade MF = new ManagerFacadeFactory().getFacade();
        ReportList reportList = MF.reportListRecived();

        return Response.status(200).entity(reportList).build();
    }
}
