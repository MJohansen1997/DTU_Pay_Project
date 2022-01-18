Feature: Report
  
  @report
  Scenario: There is two reports with the same paymentID that the manager can see
    Given the manager request the reportlist
    Then the reportlist should contain two reports with the same paymentID

  @report
  Scenario: Uniq customer report
    Given a report has paymentID
    And the report has a customerID
    Then the report is a customer report

  @report
  Scenario: Uniq merchent report
    Given a report has paymentID
    And the report doesnt have a customerID
    Then the report is a merchant report  
    
    