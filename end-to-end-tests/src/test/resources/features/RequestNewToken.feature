Feature: Request New Token
  Testing the Token Request Token feature
  @token
  Scenario: Request Tokens with 1 available tokens left
    Given a customer with a bank account with balance 1000.00
    And that the customer is registered with DTU pay
    And the customer has 0 token left
    When the customer request new Tokens
    Then the customer receives 6 new tokens
  @token  
  Scenario: Request Tokens with 3 available tokens left
    Given a customer with a bank account with balance 1000.00
    And that the customer is registered with DTU pay
    And the customer has 3 token left
    When the customer request new Tokens
    Then the customer receive the following message "You still have at least 2 unused Tokens"
