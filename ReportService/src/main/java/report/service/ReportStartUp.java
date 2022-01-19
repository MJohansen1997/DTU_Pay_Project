package report.service;

import messaging.implementations.RabbitMqQueue;
import report.service.adapter.ReportAdapter;
import report.service.storage.ReportRepository;


public class ReportStartUp {
    public static void main(String[] args) throws Exception {
        new ReportStartUp().startUp();
    }

    private void startUp() throws Exception {
        System.out.println("startup");
        var mq = new RabbitMqQueue("localhost");
//        var mq = new RabbitMqQueue("rabbitmq");
        var service = new ReportService(new ReportRepository());
        new ReportAdapter(mq, service);
    }
}
