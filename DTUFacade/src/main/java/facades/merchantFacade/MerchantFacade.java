package facades.merchantFacade;

import messaging.MessageQueue;

public class MerchantFacade {
    private MessageQueue queue;

    public MerchantFacade(MessageQueue q) {
        queue = q;
    }

}
