package com.marketplace.wishlist.domain;

public class Wish {
    private WishID customerId;
    private WishID productId;
    private String name;
    private String description;
    private Integer amount;

    public Wish(WishID customerId, WishID productId, String name, String description, Integer amount) {
        this.customerId = customerId;
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.amount = amount;
    }

    public WishID getCustomerId() {
        return customerId;
    }

    public WishID getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setCustomerId(WishID customerId) {
        this.customerId = customerId;
    }

    public void setProductId(WishID productId) {
        this.productId = productId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
