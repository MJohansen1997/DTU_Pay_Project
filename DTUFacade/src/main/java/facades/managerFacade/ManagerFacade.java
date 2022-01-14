package facades.managerFacade;

import messaging.MessageQueue;

public class ManagerFacade {
    private MessageQueue queue;

    public ManagerFacade(MessageQueue q) {
        queue = q;
    }

}
