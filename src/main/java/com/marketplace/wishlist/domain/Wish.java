package com.marketplace.wishlist.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Wish {
    private WishID customerId;
    private WishID productId;
    private String name;
    private String description;
    private Integer amount;
}
