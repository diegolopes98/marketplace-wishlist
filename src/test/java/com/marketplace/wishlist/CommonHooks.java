package com.marketplace.wishlist;

import com.marketplace.wishlist.infrastructure.persistence.WishByCustomerRepository;
import io.cucumber.java.After;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CommonHooks {

    public static Logger log = LoggerFactory.getLogger(CucumberIntegrationTest.class);

    private WishByCustomerRepository repository;

    @Autowired
    public CommonHooks(WishByCustomerRepository repository) {
        this.repository = repository;
    }

    @After
    public void afterEach() {
        log.info("------- Truncating Cassandra Tables -------");
        repository.deleteAll();
    }
}
