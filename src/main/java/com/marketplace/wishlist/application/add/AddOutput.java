package com.marketplace.wishlist.application.add;

public record AddOutput (
        String name,
        String description,
        Integer amount
) {
    public static AddOutput with(
            final String name,
            final String description,
            final Integer amount
    ) {
        return new AddOutput(name, description, amount);
    }
}