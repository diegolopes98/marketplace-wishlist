Feature: Get All Wishes
  Background:
    Given an customer id "3880bae4-4552-451b-80c1-f972acd1199b" with a mock wishlist with the product ids
      |57ab8693-b83e-409d-862b-bc4cb6ccab55|
      |9df134f3-938c-40b1-802d-59f0dea97594|
      |5c8c42a4-68f9-4539-927f-d582e6cdebed|
  Scenario: Empty Wishlist
    When the API receive a get for all wishes for a non existent customer id
    Then the response should be empty

  Scenario: Filled Wishlist
    When the API receive a get for all wishes for customer id "3880bae4-4552-451b-80c1-f972acd1199b"
    Then the response should be equals to "{\"items\":[{\"name\":\"some name\",\"description\":\"some description\",\"amount\":10000},{\"name\":\"some name\",\"description\":\"some description\",\"amount\":10000},{\"name\":\"some name\",\"description\":\"some description\",\"amount\":10000}]}"