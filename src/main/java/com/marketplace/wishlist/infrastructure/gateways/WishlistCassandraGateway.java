package com.marketplace.wishlist.infrastructure.gateways;

import com.marketplace.wishlist.domain.Wish;
import com.marketplace.wishlist.domain.WishID;
import com.marketplace.wishlist.domain.WishlistGateway;
import com.marketplace.wishlist.infrastructure.persistence.WishByCustomer;
import com.marketplace.wishlist.infrastructure.persistence.WishByCustomerRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class WishlistCassandraGateway implements WishlistGateway {
    private WishByCustomerRepository repository;

    public WishlistCassandraGateway(WishByCustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public void deleteByIds(WishID customerId, WishID productId) {
        Optional<WishByCustomer> toBeDeleted = this.repository.findByCustomerIdAndProductId(
                customerId.getUUIDValue(),
                productId.getUUIDValue()
        );
        if (toBeDeleted.isPresent()) {
            this.repository.delete(toBeDeleted.get());
        }
    }

    @Override
    public boolean existsByIds(WishID customerId, WishID productId) {
        Optional<WishByCustomer> existent = this.repository.findByCustomerIdAndProductId(
                customerId.getUUIDValue(),
                productId.getUUIDValue()
        );
        if (existent.isEmpty()) return false;
        return true;
    }

    @Override
    public Integer countById(WishID customerId) {
        return this.repository.countByCustomerId(customerId.getUUIDValue());
    }

    @Override
    public List<Wish> getAllByCustomerId(WishID customerId) {
        return this.repository
                .findAllByCustomerId(customerId.getUUIDValue())
                .stream()
                .map(entity -> new Wish(
                        WishID.from(entity.getCustomerId()),
                        WishID.from(entity.getProductId()),
                        entity.getName(),
                        entity.getDescription(),
                        entity.getAmount()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Wish> getByCustomerIdAndProductId(WishID customerId, WishID productId) {
        Optional<WishByCustomer> found = this.repository.findByCustomerIdAndProductId(
                customerId.getUUIDValue(),
                productId.getUUIDValue()
        );
        if (found.isEmpty()) return Optional.empty();
        return Optional.of(new Wish(
                WishID.from(found.get().getCustomerId()),
                WishID.from(found.get().getProductId()),
                found.get().getName(),
                found.get().getDescription(),
                found.get().getAmount()
        ));
    }

    @Override
    public Wish save(Wish newWish) {
        WishByCustomer toSave = WishByCustomer.from(newWish);
        this.repository.save(toSave);
        return newWish;
    }
}
