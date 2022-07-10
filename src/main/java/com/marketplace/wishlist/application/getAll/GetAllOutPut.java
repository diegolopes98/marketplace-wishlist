package com.marketplace.wishlist.application.getAll;

import com.marketplace.wishlist.domain.Wish;

public record GetAllOutPut(
        String name,
        String description,
        Integer amount
) {
    public static GetAllOutPut from(
            Wish wish
    ) {
        return new GetAllOutPut(wish.getName(), wish.getDescription(), wish.getAmount());
    }
}
