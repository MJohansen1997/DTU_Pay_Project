package token.service;

import messaging.implementations.RabbitMqQueue;
import token.service.adapter.TokenAdapter;
import token.service.storage.TokenRepository;

public class StartUp {
    public static void main(String[] args) throws Exception {
        new StartUp().startUp();
    }

    private void startUp() throws Exception {
        System.out.println("startup");
        //var mq = new RabbitMqQueue("localhost");
        var mq = new RabbitMqQueue("rabbitmq");
        var service = new TokenService(new TokenRepository());
        new TokenAdapter(mq, service);
    }
}
