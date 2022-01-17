package facades.managerFacade;

//import dtu.ws.fastmoney.AccountInfo;
import messaging.Event;
import messaging.MessageQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ManagerFacade {
    private MessageQueue queue;
//    private CompletableFuture<ArrayList<AccountInfo>> accountFuture;

    public ManagerFacade(MessageQueue q) {
        queue = q;
//        queue.addHandler("MerchantGetAccountsSuccessful", this::successfulMerchantGetAccounts);
    }

//    private void successfulMerchantGetAccounts(Event e) {
//        var id = e.getArgument(0, new ArrayList<AccountInfo>().getClass());
//        accountFuture.complete(id);
//    }

//    public ArrayList<AccountInfo> getAccountsManager() {
//        accountFuture = new CompletableFuture<>();
//        Event tempE = new Event("MerchantGetAccounts",  new Object[] { });
//        queue.publish(tempE);
//        return accountFuture.join();
//    }

}
