
Feature: Registering to DTU Pay
  @register
  Scenario: Customer registration to DTU Pay
    Given has a bank account with a valid bank account id
    And provides their details to DTUPay starting with first name "test", last name "Cust", CPR "bonkobonko" & Bank account id
    Then is registering with DTUPay as a customer
    And is given a customer id

  @register
  Scenario: Merchant registration to DTU Pay
    Given has a bank account with a valid bank account id
    And provides their details to DTUPay starting with first name "test", last name "Merc", CPR "bonkobonko" & Bank account id
    Then is registered with DTUPay as a merchant
    And is given a Merchant id

  @register
  Scenario: Nonexistent bank id
    Given user gives non existing bank account id
    Then is given an error message

  @register
  Scenario: Wrong registration input
    Given user gives empty input
    Then is given an error message