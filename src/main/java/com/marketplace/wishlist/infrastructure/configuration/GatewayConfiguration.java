package com.marketplace.wishlist.infrastructure.configuration;

import com.marketplace.wishlist.domain.WishlistGateway;
import com.marketplace.wishlist.infrastructure.gateways.WishlistCassandraGateway;
import com.marketplace.wishlist.infrastructure.persistence.WishByCustomerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

    @Bean
    WishlistGateway wishlistGateway(WishByCustomerRepository wishByCustomerRepository) {
        return new WishlistCassandraGateway(wishByCustomerRepository);
    }
}
