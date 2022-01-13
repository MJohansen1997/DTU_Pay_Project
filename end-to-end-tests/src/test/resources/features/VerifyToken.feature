Feature: VerifyToken
  # Enter feature description here
  @token
  Scenario: Verify unused token
    Given a customer with a bank account with balance 1000.00
    And that the customer is registered with DTU Pay
    And the customer has at least 1 unused token
    When the merchant verifies the token
    Then then he receives the message "Valid token"
  @token  
  Scenario: Verify used token
    Given a customer with a bank account with balance 1000.00
    And that the customer is registered with DTU Pay
    And the customer has at least 1 used token
    When the merchant verifies the token
    Then then he receives the message "Invalid token"
  @token  
  Scenario: Verify invalid token
    Given the merchant receives an invalid token
    When the merchant verifies the token
    Then then he receives the message "Invalid token"