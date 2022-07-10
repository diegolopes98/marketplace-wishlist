package com.marketplace.wishlist.application.delete;


import java.util.UUID;

public record DeleteInput (
        UUID customerId,
        UUID productId
) {
    public static DeleteInput with(
            final UUID customerId,
            final UUID productId
    ) {
        return new DeleteInput(customerId, productId);
    }
}