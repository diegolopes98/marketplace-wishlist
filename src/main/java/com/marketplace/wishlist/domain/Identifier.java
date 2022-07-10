package com.marketplace.wishlist.domain;

import java.util.UUID;

public abstract class Identifier {
    public abstract String getValue();
    public abstract UUID getUUIDValue();

}
