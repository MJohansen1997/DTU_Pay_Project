package RegisterTest;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegisterSteps {

    @Before
    public void setup() {
    }

    @Given("customer has a bank account with a valid account nr {int}")
    public void customerHasABankAccountWithAValidAccountNr(int arg0) {

    }

    @And("customer provides their details starting with first name {string}")
    public void customerProvidesTheirDetailsStartingWithFirstName(String arg0) {
    }

    @And("last name {string}")
    public void lastName(String arg0) {
    }

    @And("CPR number {string}")
    public void cprNumber(String arg0) {
    }

    @And("bank account nr {int}")
    public void bankAccountNr(int arg0) {
    }

    @When("DTUPay validates the given bank account nr")
    public void dtupayValidatesTheGivenBankAccountNr() {
    }

    @Then("merchant is registered with DTUPay successfully")
    public void merchantIsRegisteredWithDTUPaySuccessfully() {
    }

    @And("is given a customer id {string}")
    public void isGivenACustomerId(String arg0) {
    }

    @Given("Merchant has a bank account with a valid account nr {int}")
    public void merchantHasABankAccountWithAValidAccountNr(int arg0) {
    }

    @And("Merchant provides their details starting with first name {string}")
    public void merchantProvidesTheirDetailsStartingWithFirstName(String arg0) {
    }

    @And("is given a Merchant id {string}")
    public void isGivenAMerchantId(String arg0) {
    }
}
