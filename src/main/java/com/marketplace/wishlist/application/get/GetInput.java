package com.marketplace.wishlist.application.get;

public record GetInput(
        String customerId,
        String productId
) {
    public static GetInput with(
            String customerId,
            String productId
    ) {
        return new GetInput(customerId, productId);
    }
}
