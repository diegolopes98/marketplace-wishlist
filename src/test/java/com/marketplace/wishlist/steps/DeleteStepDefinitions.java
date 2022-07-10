package com.marketplace.wishlist.steps;

import com.marketplace.wishlist.utils.HttpResponse;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

public class DeleteStepDefinitions {

    private HttpResponse apiResponse;
    private RestTemplate restTemplate;


    public DeleteStepDefinitions(
            @Qualifier("rest-template") RestTemplate restTemplate
    ) {
        this.restTemplate = restTemplate;
        this.apiResponse = new HttpResponse();
    }

    @When("the API receive a delete for wish {string} from customer id {string}")
    public void theAPIReceiveADeleteForWishFromCustomerId(String productId, String customerId) {
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    customerId + "/wishlist/" + productId,
                    HttpMethod.DELETE,
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

    @Then("the Delete API must return {int}")
    public void theAPIMustReturn(int statusCode) {
        assert apiResponse.getStatus().equals(HttpStatus.resolve(statusCode));
    }
}
