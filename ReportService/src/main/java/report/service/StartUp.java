package report.service;

import messaging.implementations.RabbitMqQueue;
import report.service.adapter.ReportController;


public class StartUp {
    public static void main(String[] args) throws Exception {
        new StartUp().startUp();
    }

    private void startUp() throws Exception {
        System.out.println("startup");
        var mq = new RabbitMqQueue("localhost");
        new ReportController(mq);
    }
}
