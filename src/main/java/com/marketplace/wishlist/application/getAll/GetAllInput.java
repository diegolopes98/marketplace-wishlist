package com.marketplace.wishlist.application.getAll;

public record GetAllInput(
        String customerId
) {
    public static GetAllInput with(String customerId) {
        return new GetAllInput(customerId);
    }
}
