package com.marketplace.wishlist.domain;

public interface WishlistGateway {
    Wish add(Wish wish) throws Exception;
    void delete(WishID customerId, WishID productId) throws Exception;
    Wish find(WishID customerID, WishID productId) throws Exception;
    Wishlist getAll(WishID customerId) throws Exception;

}
