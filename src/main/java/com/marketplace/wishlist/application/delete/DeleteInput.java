package com.marketplace.wishlist.application.delete;


public record DeleteInput(
        String customerId,
        String productId
) {
    public static DeleteInput with(
            final String customerId,
            final String productId
    ) {
        return new DeleteInput(customerId, productId);
    }
}