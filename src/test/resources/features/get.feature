Feature: Get Specific Wish
  Background:
    Given an customer id "3880bae4-4552-451b-80c1-f972acd1199b" with a mock wishlist with the product ids
      |57ab8693-b83e-409d-862b-bc4cb6ccab55|

  Scenario: Get existent wish
    When the API receive a get for wish "57ab8693-b83e-409d-862b-bc4cb6ccab55" from customer id "3880bae4-4552-451b-80c1-f972acd1199b"
    Then the Get API must return 200

  Scenario: Get non existent wish
    When the API receive a get for wish "489217dd-8860-47d3-a2c6-625b27d88aed" from customer id "fc5d8209-cf41-4102-a851-81c715f2e921"
    Then the Get API must return 404

  Scenario: Get an unprocessable wish id
    When the API receive a get for wish "test" from customer id "fc5d8209-cf41-4102-a851-81c715f2e921"
    Then the Get API must return 422

  Scenario: Get an unprocessable customer id
    When the API receive a get for wish "57ab8693-b83e-409d-862b-bc4cb6ccab55" from customer id "test"
    Then the Get API must return 422