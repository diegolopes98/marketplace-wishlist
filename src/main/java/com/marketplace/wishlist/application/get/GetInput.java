package com.marketplace.wishlist.application.get;

import java.util.UUID;

public record GetInput(
        UUID customerId,
        UUID productId
) {
    public static GetInput with(
            UUID customerId,
            UUID productId
    ) {
        return new GetInput(customerId, productId);
    }
}
