Feature: Request New Token
  Testing the Token Request Token feature
  @token
  Scenario: Request Tokens with 0 available tokens left
    Given a customer with a bank account with balance 1000.00
    And that the customer is registered with DTU pay
    And the customer has 0 token left
    When the customer request new Tokens
    Then the customer receives 6 new tokens
    And the customer receive the following message "Tokens received"
  @token  
  Scenario: Request Tokens with 3 available tokens left
    Given a customer with a bank account with balance 1000.00
    And that the customer is registered with DTU pay
    And the customer has 6 token left
    When the customer request new Tokens
    Then the customer receives 0 new tokens
    And the customer receive the following message "Tokens not received"
