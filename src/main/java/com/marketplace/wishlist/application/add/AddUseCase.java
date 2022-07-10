package com.marketplace.wishlist.application.add;

import com.marketplace.wishlist.domain.Wish;
import com.marketplace.wishlist.domain.WishID;
import com.marketplace.wishlist.domain.WishlistGateway;

public class AddUseCase {
    private WishlistGateway gateway;

    public AddUseCase(WishlistGateway gateway) {
        this.gateway = gateway;
    }

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
