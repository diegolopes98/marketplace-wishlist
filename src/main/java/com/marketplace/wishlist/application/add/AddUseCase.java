package com.marketplace.wishlist.application.add;

import com.marketplace.wishlist.domain.Wish;
import com.marketplace.wishlist.domain.WishID;
import com.marketplace.wishlist.domain.WishlistGateway;
import com.marketplace.wishlist.domain.exceptions.BadUUIDException;
import com.marketplace.wishlist.domain.exceptions.MaxLimitExceededException;

public class AddUseCase {

    private final static Integer MAX_WISHES_LIMIT = 20;
    private WishlistGateway gateway;

    public AddUseCase(WishlistGateway gateway) {
        this.gateway = gateway;
    }

    public AddOutput execute(AddInput input) throws MaxLimitExceededException, BadUUIDException {
        WishID customerId = WishID.from(input.customerId());
        WishID productId = WishID.from(input.productId());
        this.validate(customerId);
        Wish wish = new Wish(
                customerId,
                productId,
                input.name(),
                input.description(),
                input.amount()
        );
        this.gateway.save(wish);
        return AddOutput.from(wish);
    }

    //TODO: add validation to domain layer
    private void validate(final WishID customerId) throws MaxLimitExceededException {
        Integer wishesCount = this.gateway.countById(customerId);
        if (wishesCount >= MAX_WISHES_LIMIT) {
            throw new MaxLimitExceededException(customerId.getValue());
        }
    }
}
