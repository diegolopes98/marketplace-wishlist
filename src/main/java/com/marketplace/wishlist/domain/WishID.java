package com.marketplace.wishlist.domain;

import java.util.Objects;
import java.util.UUID;

public class WishID extends Identifier {
    private final String value;

    private WishID(final String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static WishID unique() {
        return WishID.from(UUID.randomUUID());
    }

    public static WishID from(final String anId) {
        return new WishID(anId);
    }

    public static WishID from(final UUID anId) {
        return new WishID(anId.toString().toLowerCase());
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final WishID that = (WishID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
