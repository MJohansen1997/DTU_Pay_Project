Feature: Report
  
  @report
  Scenario: The Report for the manager
    Given The user is a "manager"
    Then the "manager" request the reportlist
    And the reportlist should contain all reports

  @report
  Scenario: Two reports are created after a transaction
    Given a successful transaction
    Then a customer report is created for the transaction
    And a merchant report is created for the transaction

  @report
  Scenario: The Report for the customer
    Given the user is a "customer"
    Then the "customer" requests the report
    And the report should contain all of the "customer" transaction details

  @report
  Scenario: The report for the merchant
    Given the user is a "merchant"
    Then the "merchant" requsts the reportlist
    And the report should contain all of the "merchant" transaction details
