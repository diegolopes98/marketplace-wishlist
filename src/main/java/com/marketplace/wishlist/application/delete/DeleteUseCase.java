package com.marketplace.wishlist.application.delete;

import com.marketplace.wishlist.domain.WishID;
import com.marketplace.wishlist.domain.WishlistGateway;

public class DeleteUseCase {

    private WishlistGateway gateway;

    public DeleteUseCase(WishlistGateway gateway) {
        this.gateway = gateway;
    }

    public void execute(DeleteInput input) throws Exception {
        gateway.delete(
                WishID.from(input.customerId()),
                WishID.from(input.productId())
        );
    }
}
