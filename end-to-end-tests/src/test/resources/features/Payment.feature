Feature: Payment
  @payment
  Scenario: Successful Payment
    Given customer with a bank account with balance 1000.00
    And that the customer is registered with DTU Pay
    And the customer requests new tokens
    Given a merchant with a bank account with balance 2000.00
    And that the merchant is registered with DTU Pay
    When the merchant initiates a payment for 100 kr by the customer
    Then the payment is "successful"
    And the balance of the customer at the bank is 900.00 kr
    And the balance of the merchant at the bank is 2100.00 kr

  @payment
  Scenario: Fail Payment due to invalid token
    Given a customer with an ID
    And the token is invalid
    When the merchant initiates a payment for 100 kr by the customer1
    Then the payment is denied

@payment
  Scenario: Fail Payment due to already consumed token
        Given customer with a bank account with balance 1000.00
        And that the customer is registered with DTU Pay
        And the customer requests new tokens
        Given a merchant with a bank account with balance 2000.00
        And that the merchant is registered with DTU Pay
        And the merchant initiates a payment for 100 kr by the customer
        And the payment is "successful"
        When the merchant initiates a payment with the same token
        Then the payment is denied



