package com.marketplace.wishlist.application.add;

import com.marketplace.wishlist.domain.Wish;

public record AddOutput(
        String name,
        String description,
        Integer amount
) {
    public static AddOutput from(
            final Wish wish
    ) {
        return new AddOutput(wish.getName(), wish.getDescription(), wish.getAmount());
    }
}