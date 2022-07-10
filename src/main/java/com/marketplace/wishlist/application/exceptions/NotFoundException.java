package com.marketplace.wishlist.application.exceptions;

public class NotFoundException extends Exception {
    private static String NOT_FOUND_MESSAGE = "Not found wish with id %s";

    public NotFoundException(String notFoundId) {
        super(
                String.format(
                        NOT_FOUND_MESSAGE,
                        notFoundId
                )
        );
    }
}
