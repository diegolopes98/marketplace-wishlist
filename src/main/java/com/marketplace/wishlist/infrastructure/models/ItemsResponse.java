package com.marketplace.wishlist.infrastructure.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ItemsResponse(
    @JsonProperty("name") String name,
    @JsonProperty("description") String description,
    @JsonProperty("amount") Integer amount
    ){}
