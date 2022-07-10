package com.marketplace.wishlist.domain;

import java.util.List;
import java.util.Optional;

public interface WishlistGateway {
    void deleteByIds(final WishID customerId, final WishID productId);

    boolean existsByIds(final WishID customerId, final WishID productId);

    Integer countById(final WishID customerId);

    List<Wish> getAllByCustomerId(final WishID customerId);

    Optional<Wish> getByCustomerIdAndProductId(final WishID customerId, final WishID productId);

    Wish save(final Wish newWish);
}
