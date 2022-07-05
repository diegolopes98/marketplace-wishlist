package com.marketplace.wishlist.infrastructure.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record WishlistResponse (
    @JsonProperty("items") List<ItemsResponse> items
) {}
