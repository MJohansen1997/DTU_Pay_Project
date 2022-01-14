package facades.merchantFacade;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/merchant")
public class MerchantResource {
    @GET
    public String helloWorld() {
        return "hello world";
    }
}
