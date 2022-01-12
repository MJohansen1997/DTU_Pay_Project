import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TokenSteps {

    @Given("that the customer is registered with DTU pay")
    public void that_the_customer_is_registered_with_dtu_pay() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Given("the customer has {int} token left")
    public void the_customer_has_token_left(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("the customer request new Tokens")
    public void the_customer_request_new_tokens() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("the customer receives {int} new tokens")
    public void the_customer_receives_new_tokens(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the customer receive the following message {string}")
    public void the_customer_receive_the_following_message(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("a customer with a bank account with balance {double}")
    public void aCustomerWithABankAccountWithBalance(int arg0) {
    }

    @And("that the customer is registered with DTU Pay")
    public void thatTheCustomerIsRegisteredWithDTUPay() {
    }

    @And("the customer has at least {int} unused token")
    public void theCustomerHasAtLeastUnusedToken(int arg0) {
    }

    @When("the merchant verifies the token")
    public void theMerchantVerifiesTheToken() {
    }

    @Then("then he receives the message {string}")
    public void thenHeReceivesTheMessage(String arg0) {
    }

    @And("the customer has at least {int} used token")
    public void theCustomerHasAtLeastUsedToken(int arg0) {
    }

    @Given("the merchant receives an invalid token")
    public void theMerchantReceivesAnInvalidToken() {
    }
}
