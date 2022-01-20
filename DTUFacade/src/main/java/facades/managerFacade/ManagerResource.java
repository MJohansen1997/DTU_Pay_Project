package facades.managerFacade;


import facades.DTO.ReportList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

//Default rest path to access manager
@Path("/manager")
public class ManagerResource {

    //Will initiate a request for all reports
    @Path("reports")
    @GET
    @Produces("application/json")
    public Response getReports() {
        ManagerFacade MF = new ManagerFacadeFactory().getFacade();
        ReportList reportList = MF.reportListRecived();

        return Response.status(200).entity(reportList).build();
    }
}
