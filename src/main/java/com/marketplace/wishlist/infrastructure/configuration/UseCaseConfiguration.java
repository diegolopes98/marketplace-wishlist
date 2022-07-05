package com.marketplace.wishlist.infrastructure.configuration;

import com.marketplace.wishlist.application.wishlist.add.AddUseCase;
import com.marketplace.wishlist.application.wishlist.add.DefaultAddUseCase;
import com.marketplace.wishlist.application.wishlist.delete.DefaultDeleteUseCase;
import com.marketplace.wishlist.application.wishlist.delete.DeleteUseCase;
import com.marketplace.wishlist.application.wishlist.find.DefaultFindUseCase;
import com.marketplace.wishlist.application.wishlist.find.FindUseCase;
import com.marketplace.wishlist.application.wishlist.getAll.DefaultGetAllUseCase;
import com.marketplace.wishlist.application.wishlist.getAll.GetAllUseCase;
import com.marketplace.wishlist.domain.WishlistGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public AddUseCase addUseCase(
            WishlistGateway gateway
    ) {
        return new DefaultAddUseCase(gateway);
    }

    @Bean
    public DeleteUseCase deleteUseCase (
            WishlistGateway gateway
    ) {
        return new DefaultDeleteUseCase(gateway);
    }

    @Bean
    public FindUseCase findUseCase (
            WishlistGateway gateway
    ) {
        return new DefaultFindUseCase(gateway);
    }

    @Bean
    public GetAllUseCase getAllUseCase (
            WishlistGateway gateway
    ) {
        return new DefaultGetAllUseCase(gateway);
    }
}
