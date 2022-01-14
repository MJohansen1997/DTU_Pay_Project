package facades.customerFacade;

import messaging.Event;
import messaging.MessageQueue;
import studentregistration.service.Student;

import java.util.concurrent.CompletableFuture;

public class CustomerFacade {
    private MessageQueue queue;

    public CustomerFacade(MessageQueue q) {
        queue = q;
    }
    
}
