package com.marketplace.wishlist.steps;

import com.marketplace.wishlist.SpringIntegrationTest;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.client.RestTemplate;

public class TestStepDefinitions extends SpringIntegrationTest {

    private RestTemplate restTemplate;

    @Autowired
    public TestStepDefinitions(
            @Qualifier("rest-template") RestTemplate restTemplate
    ) {
        this.restTemplate = restTemplate;
    }

    @Then("true should be true")
    public void trueShouldBeTrue() {
        assert true;
    }
}
