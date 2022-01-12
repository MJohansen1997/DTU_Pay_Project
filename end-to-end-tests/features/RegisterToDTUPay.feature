Feature: Registering to DTU Pay
  Scenario: Customer registration to DTU Pay
    Given has a bank account with a valid bank account id
    And provides their details to DTUPay starting with first name "", last name "", CPR "" & Bank account id
    Then is registering with DTUPay as a customer
    And is given a customer id

  Scenario: Merchant registration to DTU Pay
    Given has a bank account with a valid bank account id
    And provides their details to DTUPay starting with first name "", last name "", CPR "" & Bank account id
    Then is registered with DTUPay as a merchant
    And is given a Merchant id

