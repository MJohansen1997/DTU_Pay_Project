Scenario: Refund - success
Given a customer with a customerID 123
And the customer find the paymentID which is 456
Then the customer gets his money refunded

Scenario: Refund - fail
Given a customer with a wrong paymentID 231
When the customer decides that he wants a refund
And the customer finds the payment id of his transaction
Then the customer doesn't gets the money refunded