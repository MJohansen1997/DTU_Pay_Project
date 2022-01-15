package facades;

import javax.ws.rs.POST;
import javax.ws.rs.core.Response;

public interface IMerchant extends IUser {
    @POST
    Response payment();
}
