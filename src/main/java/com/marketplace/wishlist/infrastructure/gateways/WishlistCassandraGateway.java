package com.marketplace.wishlist.infrastructure.gateways;

import com.marketplace.wishlist.domain.Wish;
import com.marketplace.wishlist.domain.WishID;
import com.marketplace.wishlist.domain.Wishlist;
import com.marketplace.wishlist.domain.WishlistGateway;
import com.marketplace.wishlist.infrastructure.persistence.WishByCustomer;
import com.marketplace.wishlist.infrastructure.persistence.WishByCustomerRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class WishlistCassandraGateway implements WishlistGateway {

    private static final Integer MAX_SIZE = 20;
    private WishByCustomerRepository repository;

    public WishlistCassandraGateway(WishByCustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Wish add(Wish wish) throws Exception {
        WishByCustomer newWish = new WishByCustomer(
                UUID.fromString(wish.getCustomerId().getValue()),
                UUID.fromString(wish.getProductId().getValue()),
                wish.getName(),
                wish.getDescription(),
                wish.getAmount()
        );

        if (!this.validate(newWish.getCustomerId())) {
            throw new Exception("Max items reached in wishlist");
        }

        repository.save(newWish);

        return wish;
    }

    // TODO: add validation to domain layer
    private boolean validate(UUID customerId) {
        return this.repository.countByCustomerId(customerId) < MAX_SIZE;
    }

    @Override
    public void delete(WishID customerId, WishID productId) throws Exception {
        Optional<WishByCustomer> wishToDelete = repository
                .findByCustomerIdAndProductId(
                        UUID.fromString(customerId.getValue()),
                        UUID.fromString(productId.getValue())
                );
        if (wishToDelete.isEmpty()) {
            throw new Exception("Not found product in wishlist");
        }
        this.repository.delete(wishToDelete.get());
    }

    @Override
    public Wish find(WishID customerID, WishID productId) throws Exception {
        Optional<WishByCustomer> wish = this.repository.findByCustomerIdAndProductId(
                UUID.fromString(customerID.getValue()),
                UUID.fromString(productId.getValue())
        );
        if (wish.isEmpty()) {
            throw new Exception("Item not found in wishlist");
        }
        Wish found = new Wish(
                WishID.from(wish.get().getCustomerId()),
                WishID.from(wish.get().getProductId()),
                wish.get().getName(),
                wish.get().getDescription(),
                wish.get().getAmount()
        );

        return found;
    }

    @Override
    public Wishlist getAll(WishID customerId) throws Exception {
        List<WishByCustomer> wishes = this.repository.findByCustomerId(UUID.fromString(customerId.getValue()));
        List<Wish> wishesMapped = wishes
                .stream()
                .map((wish) -> new Wish(
                        WishID.from(wish.getCustomerId()),
                        WishID.from(wish.getProductId()),
                        wish.getName(),
                        wish.getDescription(),
                        wish.getAmount()
                ))
                .collect(Collectors.toList());
        Wishlist wishlist = new Wishlist(wishesMapped);
        return wishlist;
    }
}
