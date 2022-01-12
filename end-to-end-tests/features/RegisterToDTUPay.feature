Feature: Registering to DTU Pay
  Scenario: Customer registration to DTU Pay
    Given customer has a bank account with a valid account nr 12345
    And customer provides their details starting with first name ""
    And last name ""
    And CPR number ""
    And bank account nr 12345
    When DTUPay validates the given bank account nr
    Then merchant is registered with DTUPay successfully
    And is given a customer id ""

  Scenario: Merchant registration to DTU Pay
    Given Merchant has a bank account with a valid account nr 12345
    And Merchant provides their details starting with first name ""
    And last name ""
    And CPR number ""
    And bank account nr 12345
    When DTUPay validates the given bank account nr
    Then merchant is registered with DTUPay successfully
    And is given a Merchant id ""
