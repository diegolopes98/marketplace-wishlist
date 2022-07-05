package com.marketplace.wishlist.application.wishlist.delete;

import com.marketplace.wishlist.domain.WishID;
import com.marketplace.wishlist.domain.WishlistGateway;

public class DefaultDeleteUseCase extends DeleteUseCase {

    private WishlistGateway gateway;

    public DefaultDeleteUseCase(WishlistGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public void execute(DeleteInput input) throws Exception {
        gateway.delete(
                WishID.from(input.customerId()),
                WishID.from(input.productId())
        );
    }
}
