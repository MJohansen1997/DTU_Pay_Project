#Feature: Refund
#
#Scenario: Refund - success
#    Given the customers paymentID is "p456"
#    And the amount for this payment is 100 kr
#    When the refund is "completed"
#    Then the balance of the merchant at the bank is 2100.00 kr
#    And the balance of the customer at the bank is 900.00 kr
#
#Scenario: Refund - fail
#    Given a customer with a wrong paymentID "p231"
#    When the refund is "not completed"
#    Then the balance of the merchant at the bank is 2000.00 kr
#    And the balance of the customer at the bank is 1000.00 kr