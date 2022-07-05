package com.marketplace.wishlist.application.wishlist.find;

import java.util.UUID;

public record FindInput(
        UUID customerId,
        UUID productId
) {
    public static FindInput with(
            UUID customerId,
            UUID productId
    ) {
        return new FindInput(customerId, productId);
    }
}
