package com.marketplace.wishlist.application.get;

public record GetOutPut(
        String name,
        String description,
        Integer amount
) {
    public static GetOutPut with(
            String name,
            String description,
            Integer amount
    ) {
        return new GetOutPut(name, description, amount);
    }
}
