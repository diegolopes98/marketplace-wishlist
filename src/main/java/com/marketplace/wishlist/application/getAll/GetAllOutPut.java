package com.marketplace.wishlist.application.getAll;

public record GetAllOutPut (
        String name,
        String description,
        Integer amount
) {
    public static GetAllOutPut with(
            String name,
            String description,
            Integer amount
    ) {
        return new GetAllOutPut(name, description, amount);
    }
}
