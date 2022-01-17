package account.service;
import account.service.adapter.FacadeAdapter;
import account.service.repository.AccountRepositoryAdapter;
import messaging.implementations.RabbitMqQueue;

public class StartUp {
    public static void main(String[] args) throws Exception {
            new StartUp().startUp();
        }

        private void startUp() throws Exception {
            System.out.println("startup");
            var mq = new RabbitMqQueue("localhost");
            new FacadeAdapter(mq, new AccountService(new AccountRepositoryAdapter()));
        }
}
