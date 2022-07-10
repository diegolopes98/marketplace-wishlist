package com.marketplace.wishlist.application.add;

import java.util.UUID;

public record AddInput (
        UUID customerId,
        UUID productId,
        String name,
        String description,
        Integer amount
) {
    public static AddInput with(
            final UUID customerId,
            final UUID productId,
            final String name,
            final String description,
            final Integer amount
    ) {
        return new AddInput(customerId, productId, name, description, amount);
    }
}
