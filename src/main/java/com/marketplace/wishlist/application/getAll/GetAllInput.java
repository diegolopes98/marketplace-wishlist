package com.marketplace.wishlist.application.getAll;

import java.util.UUID;

public record GetAllInput (
        UUID customerId
) {
    public static GetAllInput with(UUID customerId) {
        return new GetAllInput(customerId);
    }
}
