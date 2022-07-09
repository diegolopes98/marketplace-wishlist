Feature: Add Wish
  Background:
    Given a customer id "d65e14c7-67b0-4772-8bfe-fb9bacb13125" with wishes at limit
  Scenario: Add new wish to customer
    When the API receive a post for customer id "95670e4b-62f3-4d84-9b46-0d897f730c99" and product id "4c994c7d-f808-4d6c-b08c-587d72775930"
    Then the post API should return the created wish
  Scenario: Add new wish to customer at limit
    When the API receive a post for customer id "d65e14c7-67b0-4772-8bfe-fb9bacb13125" and product id "4c994c7d-f808-4d6c-b08c-587d72775930"
    Then the post API should return 400