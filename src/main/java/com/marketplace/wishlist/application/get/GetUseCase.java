package com.marketplace.wishlist.application.get;

import com.marketplace.wishlist.application.exceptions.NotFoundException;
import com.marketplace.wishlist.domain.Wish;
import com.marketplace.wishlist.domain.WishID;
import com.marketplace.wishlist.domain.WishlistGateway;
import com.marketplace.wishlist.domain.exceptions.BadUUIDException;

import java.util.Optional;

public class GetUseCase {

    private WishlistGateway gateway;

    public GetUseCase(WishlistGateway gateway) {
        this.gateway = gateway;
    }

    public GetOutPut execute(GetInput input) throws BadUUIDException, NotFoundException {
        WishID customerId = WishID.from(input.customerId());
        WishID productId = WishID.from(input.productId());
        Optional<Wish> wish = this.gateway.getByCustomerIdAndProductId(customerId, productId);
        if (wish.isEmpty()) {
            throw new NotFoundException(input.productId());
        }
        return GetOutPut.from(wish.get());
    }
}
