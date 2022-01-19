package payment.service;

import messaging.implementations.RabbitMqQueue;
import payment.service.adapter.PaymentController;

public class PaymentStartUp {
    public static void main(String[] args) throws Exception {
        new PaymentStartUp().startUp();
    }

    private void startUp() throws Exception {
        System.out.println("startup");
        var mq = new RabbitMqQueue("rabbitmq");
//        var mq = new RabbitMqQueue("localhost");
        new PaymentController(mq);
    }
}
