package com.marketplace.wishlist.domain;

import com.marketplace.wishlist.domain.exceptions.BadUUIDException;

import java.util.Objects;
import java.util.UUID;

public class WishID extends Identifier {
    private final UUID value;

    private WishID(final UUID value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static WishID from(final String anId) throws BadUUIDException {
        try {
            UUID value = UUID.fromString(anId);
            return new WishID(value);
        } catch (Exception e) {
            throw new BadUUIDException(anId);
        }
    }

    public static WishID from(final UUID anId) {
        return new WishID(anId);
    }

    @Override
    public String getValue() {
        return value.toString();
    }

    @Override
    public UUID getUUIDValue() {
        return value;
    }
}
