package com.marketplace.wishlist.infrastructure.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ErrorResponse(
        @JsonProperty("message") String message,
        @JsonProperty("status") Integer status
) {
}
