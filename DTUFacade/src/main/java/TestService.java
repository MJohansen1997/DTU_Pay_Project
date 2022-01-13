import messaging.Event;
import messaging.MessageQueue;

import java.util.concurrent.CompletableFuture;

public class TestService {

    MessageQueue queue;
    CompletableFuture<String> testFuture;


    public TestService(MessageQueue q) {
        queue = q;
        queue.addHandler("MerchantRegisteredSuccessfully", this::handleSuccessfulRegister);
    }

    public String brokerTest() {
        testFuture = new CompletableFuture<>();
        queue.publish(new Event(("MerchantRegister"), new Object[] {"Test"}));
        return testFuture.join();
    }

    private void handleSuccessfulRegister(Event event) {
        String s = event.getArgument(0,String.class);
        System.out.println("User registered successfully: " + s);
        testFuture.complete(s);
    }
}
