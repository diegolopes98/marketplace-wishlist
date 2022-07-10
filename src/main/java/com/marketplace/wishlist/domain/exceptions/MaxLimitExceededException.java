package com.marketplace.wishlist.domain.exceptions;

public class MaxLimitExceededException extends Exception {
    private final static String MAX_LIMIT_EXCEEDED_MESSAGE = "customer %s reached the max limit of wishes";

    public MaxLimitExceededException(String customerId) {
        super(
                String.format(
                        MAX_LIMIT_EXCEEDED_MESSAGE,
                        customerId
                )
        );
    }
}
