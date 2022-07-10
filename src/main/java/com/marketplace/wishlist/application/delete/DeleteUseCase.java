package com.marketplace.wishlist.application.delete;

import com.marketplace.wishlist.application.exceptions.NotFoundException;
import com.marketplace.wishlist.domain.WishID;
import com.marketplace.wishlist.domain.WishlistGateway;
import com.marketplace.wishlist.domain.exceptions.BadUUIDException;

public class DeleteUseCase {

    private WishlistGateway gateway;

    public DeleteUseCase(WishlistGateway gateway) {
        this.gateway = gateway;
    }

    public void execute(DeleteInput input) throws NotFoundException, BadUUIDException {
        WishID customerId = WishID.from(input.customerId());
        WishID productId = WishID.from(input.productId());
        if (!this.gateway.existsByIds(customerId, productId)) {
            throw new NotFoundException(productId.getValue());
        }
        this.gateway.deleteByIds(customerId, productId);
    }
}
