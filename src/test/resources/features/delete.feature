Feature: Delete Wishes
  Background:
    Given an customer id "3880bae4-4552-451b-80c1-f972acd1199b" with a mock wishlist with the product ids
      |57ab8693-b83e-409d-862b-bc4cb6ccab55|

  Scenario: Delete existent wish
    When the API receive a delete for wish "57ab8693-b83e-409d-862b-bc4cb6ccab55" from customer id "3880bae4-4552-451b-80c1-f972acd1199b"
    Then the API must return 204

  Scenario: Delete non existent wish
    When the API receive a delete for wish "489217dd-8860-47d3-a2c6-625b27d88aed" from customer id "fc5d8209-cf41-4102-a851-81c715f2e921"
    Then the API must return 404