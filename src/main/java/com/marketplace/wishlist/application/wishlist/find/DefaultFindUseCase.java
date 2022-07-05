package com.marketplace.wishlist.application.wishlist.find;

import com.marketplace.wishlist.domain.Wish;
import com.marketplace.wishlist.domain.WishID;
import com.marketplace.wishlist.domain.WishlistGateway;

public class DefaultFindUseCase extends FindUseCase {

    private WishlistGateway gateway;

    public DefaultFindUseCase(WishlistGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public FindOutPut execute(FindInput input) throws Exception {
        Wish wish = this.gateway.find(
                WishID.from(input.customerId()),
                WishID.from(input.productId())
        );
        return FindOutPut.with(
                wish.getName(),
                wish.getDescription(),
                wish.getAmount()
        );
    }
}
