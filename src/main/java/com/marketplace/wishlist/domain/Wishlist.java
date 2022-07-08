package com.marketplace.wishlist.domain;

import java.util.LinkedList;
import java.util.List;

public class Wishlist {
    private final List<Wish> items;

    public Wishlist(List<Wish> items) {
        this.items = items;
    }

    public Wishlist() {
        this.items = new LinkedList<>();
    }

    public List<Wish> getItems() {
        return items;
    }
}
