package com.marketplace.wishlist.steps;

import com.marketplace.wishlist.SpringIntegrationTest;
import com.marketplace.wishlist.infrastructure.persistence.WishByCustomer;
import com.marketplace.wishlist.infrastructure.persistence.WishByCustomerRepository;
import io.cucumber.java.en.Given;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class CommonStepDefinitions extends SpringIntegrationTest {


    private WishByCustomerRepository repository;

    public CommonStepDefinitions(WishByCustomerRepository repository) {
        this.repository = repository;
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

    @Given("a customer id {string} with wishes at limit")
    public void aCustomerIdWithWishesAtLimit(String customerId) {
        List<WishByCustomer> wishes = new LinkedList<>();
        for (int i = 0; i < 20; i++) {
            wishes.add(
                    new WishByCustomer(
                            UUID.fromString(customerId),
                            UUID.randomUUID(),
                            "some name",
                            "some description",
                            10000
                    )
            );
        }
        this.repository.saveAll(wishes);
        this.repository.countByCustomerId(UUID.fromString(customerId));
    }
}
