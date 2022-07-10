package com.marketplace.wishlist.application.add;

public record AddInput(
        String customerId,
        String productId,
        String name,
        String description,
        Integer amount
) {
    public static AddInput with(
            final String customerId,
            final String productId,
            final String name,
            final String description,
            final Integer amount
    ) {
        return new AddInput(customerId, productId, name, description, amount);
    }
}
