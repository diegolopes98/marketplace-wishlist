package com.marketplace.wishlist.steps;

import com.marketplace.wishlist.infrastructure.models.ItemsRequest;
import com.marketplace.wishlist.utils.HttpResponse;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

public class AddStepDefinitions {

    public final String DEFAULT_RESPONSE = "{\"name\":\"some name\",\"description\":\"some description\",\"amount\":10000}";
    private HttpResponse apiResponse;
    private RestTemplate restTemplate;

    public AddStepDefinitions(@Qualifier("rest-template") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.apiResponse = new HttpResponse();
    }

    @When("the API receive a post for customer id {string} and product id {string}")
    public void theAPIReceiveAPostForCustomerId(String customerId, String productId) throws JSONException {
        try {
            ItemsRequest newWish = new ItemsRequest(
                    productId,
                    "some name",
                    "some description",
                    10000
            );
            ResponseEntity<String> response = restTemplate.exchange(
                    customerId + "/wishlist",
                    HttpMethod.POST,
                    new HttpEntity<>(newWish),
                    String.class
            );
            apiResponse.setStatus(response.getStatusCode());
            apiResponse.setBody(response.getBody());
        } catch (HttpStatusCodeException e) {
            apiResponse.setStatus(e.getStatusCode());
            apiResponse.setBody(e.getResponseBodyAsString());
        }
    }

    @Then("the post API should return the created wish")
    public void thePostAPIShouldReturnTheCreatedWish() throws JSONException {
        JSONAssert.assertEquals(DEFAULT_RESPONSE, apiResponse.getBody(), false);
    }

    @Then("the post API should return {int}")
    public void thePostAPIShouldReturn(int status) {
        assert apiResponse.getStatus().equals(HttpStatus.resolve(status));
    }
}
