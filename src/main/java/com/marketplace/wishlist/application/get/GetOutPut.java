package com.marketplace.wishlist.application.get;

import com.marketplace.wishlist.domain.Wish;

public record GetOutPut(
        String name,
        String description,
        Integer amount
) {
    public static GetOutPut from(
            Wish wish
    ) {
        return new GetOutPut(wish.getName(), wish.getDescription(), wish.getAmount());
    }
}
