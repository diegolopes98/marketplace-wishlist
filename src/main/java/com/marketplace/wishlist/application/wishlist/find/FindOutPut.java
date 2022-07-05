package com.marketplace.wishlist.application.wishlist.find;

public record FindOutPut(
        String name,
        String description,
        Integer amount
) {
    public static FindOutPut with(
            String name,
            String description,
            Integer amount
    ) {
        return new FindOutPut(name, description, amount);
    }
}
