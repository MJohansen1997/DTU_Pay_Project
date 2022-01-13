Feature: Payment
  @payment
  Scenario: Successful Payment
    Given a customer with a bank account with balance 1000.00
    And that the customer is registered with DTU Pay
    And the customer has 3 tokens
    Given a merchant with a bank account with balance 2000.00
    And that the merchant is registered with DTU Pay
    When the merchant initiates a payment for 100 kr by the customer
    Then the payment is successful
    And the balance of the customer at the bank is 900.00 kr
    And the balance of the merchant at the bank is 2100.00 kr

  @payment
  Scenario: Fail Payment due to wrong ID
    Given a customer with the ID "c123"
    And the customer has 3 tokens
    And that the customer is registered with DTU Pay
    And a merchant with the ID "m321"
    When the merchant initiates a payment for 100 kr by the customer
    Given the merchant select the customer ID, which is 213
    Then the payment is denied
    And the balance of the customer at the bank is 1000.00 kr
    And the balance of the merchant at the bank is 2000.00 kr

  @payment
  Scenario: Fail Payment due to not enough tokens
    Given a customer with the ID "c123"
    And the customer has 0 tokens
    When the merchant initiates a payment for 100 kr by the customer
    Then the payment is "denied"

  