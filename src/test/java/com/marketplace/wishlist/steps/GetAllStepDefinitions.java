package com.marketplace.wishlist.steps;

import com.marketplace.wishlist.SpringIntegrationTest;
import com.marketplace.wishlist.infrastructure.persistence.WishByCustomer;
import com.marketplace.wishlist.infrastructure.persistence.WishByCustomerRepository;
import com.marketplace.wishlist.utils.HttpResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class GetAllStepDefinitions extends SpringIntegrationTest {

    private final String EMPTY_RESPONSE = "{\"items\":[]}";
    private HttpResponse apiResponse;
    private WishByCustomerRepository repository;
    private RestTemplate restTemplate;

    @Autowired
    public GetAllStepDefinitions(
            @Qualifier("rest-template") RestTemplate restTemplate,
            WishByCustomerRepository repository
    ) {
        this.restTemplate = restTemplate;
        this.repository = repository;
        this.apiResponse = new HttpResponse();
    }

    @When("the API receive a get for all wishes for customer id {string}")
    public void theAPIReceiveAGetForAllWishesForCustomerId(String customerId) {
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    customerId + "/wishlist",
                    HttpMethod.GET,
                    new HttpEntity<>(null),
                    String.class
            );
            apiResponse.setStatus(response.getStatusCode());
            apiResponse.setBody(response.getBody());
        } catch (HttpStatusCodeException e) {
            apiResponse.setStatus(e.getStatusCode());
            apiResponse.setBody(e.getResponseBodyAsString());
        }
    }

    @When("the API receive a get for all wishes for a non existent customer id")
    public void theAPIReceiveAGetForAllWishesForANonExistentCustomerId() {
        theAPIReceiveAGetForAllWishesForCustomerId(UUID.randomUUID().toString());
    }

    @Then("the response should be empty")
    public void theResponseShouldBeEmpty() throws JSONException {
        JSONAssert.assertEquals(apiResponse.getBody(), EMPTY_RESPONSE, false);
    }

    @Then("the response should be equals to {string}")
    public void theResponseShouldBeEqualsTo(String expected) throws JSONException {
        JSONAssert.assertEquals(apiResponse.getBody(), expected, false);
    }

    @Given("an customer id {string} with a mock wishlist with the product ids")
    public void anCustomerIdWithAMockWishlist(String customerId, List<String> productIds) {
        List<WishByCustomer> wishes = new LinkedList<>();
        for (int i = 0; i < productIds.size(); i++) {
            wishes.add(
                    new WishByCustomer(
                            UUID.fromString(customerId),
                            UUID.fromString(productIds.get(i)),
                            "some name",
                            "some description",
                            10000
                    )
            );
        }
        this.repository.saveAll(wishes);
    }
}
