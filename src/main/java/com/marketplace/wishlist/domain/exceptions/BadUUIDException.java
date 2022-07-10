package com.marketplace.wishlist.domain.exceptions;

public class BadUUIDException extends Exception {
    private static final String BAD_UUID_MESSAGE = "received id \"%s\" isn't valid";

    public BadUUIDException(String invalidId) {
        super(
                String.format(
                        BAD_UUID_MESSAGE,
                        invalidId
                )
        );
    }
}
