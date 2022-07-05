package com.marketplace.wishlist.application.wishlist.add;

import com.marketplace.wishlist.domain.Wish;
import com.marketplace.wishlist.domain.WishID;
import com.marketplace.wishlist.domain.WishlistGateway;

public class DefaultAddUseCase extends AddUseCase {
    private WishlistGateway gateway;

    public DefaultAddUseCase(WishlistGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public AddOutput execute(AddInput input) throws Exception {
        Wish wishToAdd = new Wish(
                WishID.from(input.customerId()),
                WishID.from(input.productId()),
                input.name(),
                input.description(),
                input.amount()
        );
        Wish newWish = this.gateway.add(wishToAdd);
        return AddOutput.with(
                newWish.getName(),
                newWish.getDescription(),
                newWish.getAmount()
        );
    }
}
