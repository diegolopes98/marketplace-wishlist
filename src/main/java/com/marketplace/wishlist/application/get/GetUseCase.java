package com.marketplace.wishlist.application.get;

import com.marketplace.wishlist.domain.Wish;
import com.marketplace.wishlist.domain.WishID;
import com.marketplace.wishlist.domain.WishlistGateway;

public class GetUseCase {

    private WishlistGateway gateway;

    public GetUseCase(WishlistGateway gateway) {
        this.gateway = gateway;
    }

    public GetOutPut execute(GetInput input) throws Exception {
        Wish wish = this.gateway.find(
                WishID.from(input.customerId()),
                WishID.from(input.productId())
        );
        return GetOutPut.with(
                wish.getName(),
                wish.getDescription(),
                wish.getAmount()
        );
    }
}
