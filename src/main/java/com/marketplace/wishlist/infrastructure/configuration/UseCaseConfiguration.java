package com.marketplace.wishlist.infrastructure.configuration;

import com.marketplace.wishlist.application.add.AddUseCase;
import com.marketplace.wishlist.application.delete.DeleteUseCase;
import com.marketplace.wishlist.application.get.GetUseCase;
import com.marketplace.wishlist.application.getAll.GetAllUseCase;
import com.marketplace.wishlist.domain.WishlistGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public AddUseCase addUseCase(
            WishlistGateway gateway
    ) {
        return new AddUseCase(gateway);
    }

    @Bean
    public DeleteUseCase deleteUseCase (
            WishlistGateway gateway
    ) {
        return new DeleteUseCase(gateway);
    }

    @Bean
    public GetUseCase findUseCase (
            WishlistGateway gateway
    ) {
        return new GetUseCase(gateway);
    }

    @Bean
    public GetAllUseCase getAllUseCase (
            WishlistGateway gateway
    ) {
        return new GetAllUseCase(gateway);
    }
}
