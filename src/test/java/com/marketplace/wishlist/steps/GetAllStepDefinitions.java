package com.marketplace.wishlist.steps;

import com.marketplace.wishlist.SpringIntegrationTest;
import com.marketplace.wishlist.utils.HttpResponse;
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

import java.util.UUID;

public class GetAllStepDefinitions {

    private final String EMPTY_RESPONSE = "{\"items\":[]}";
    private HttpResponse apiResponse;
    private RestTemplate restTemplate;

    @Autowired
    public GetAllStepDefinitions(
            @Qualifier("rest-template") RestTemplate restTemplate
    ) {
        this.restTemplate = restTemplate;
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
}
