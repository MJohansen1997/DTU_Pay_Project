package facades.merchantFacade;

import facades.customerFacade.CustomerFacade;
import messaging.implementations.RabbitMqQueue;

public class MerchantFacadeFactory {
    static MerchantFacade facade = null;

    public MerchantFacade getFacade() {
        // The singleton pattern.
        // Ensure that there is at most
        // one instance of a PaymentService
        if (facade != null) {
            return facade;
        }

        // Hookup the classes to send and receive
        // messages via RabbitMq, i.e. RabbitMqSender and
        // RabbitMqListener.
        // This should be done in the factory to avoid
        // the PaymentService knowing about them. This
        // is called dependency injection.
        // At the end, we can use the PaymentService in tests
        // without sending actual messages to RabbitMq.
        var mq = new RabbitMqQueue("localhost");
        facade = new MerchantFacade(mq);
//		new StudentRegistrationServiceAdapter(service, mq);
        return facade;
    }
}
