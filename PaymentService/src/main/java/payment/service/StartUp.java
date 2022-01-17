package payment.service;

import messaging.implementations.RabbitMqQueue;
import payment.service.adapter.PaymentController;

public class StartUp {
    public static void main(String[] args) throws Exception {
        new StartUp().startUp();
    }

    private void startUp() throws Exception {
        System.out.println("startup");
        var mq = new RabbitMqQueue("localhost");
        new PaymentController(mq);
    }
}
