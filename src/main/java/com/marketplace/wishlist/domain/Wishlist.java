package com.marketplace.wishlist.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class Wishlist {
    private final List<Wish> items;

    public Wishlist(List<Wish> items) {
        this.items = items;
    }

    public Wishlist() {
        this.items = new LinkedList<>();
    }
}
