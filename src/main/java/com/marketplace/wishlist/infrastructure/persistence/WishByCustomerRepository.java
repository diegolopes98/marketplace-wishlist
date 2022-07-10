package com.marketplace.wishlist.infrastructure.persistence;

import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WishByCustomerRepository extends CassandraRepository<WishByCustomer, UUID> {
    Integer countByCustomerId(UUID customerId);

    List<WishByCustomer> findAllByCustomerId(UUID customerId);

    default Optional<WishByCustomer> findByCustomerIdAndProductId(UUID customerId, UUID productId) {
        List<WishByCustomer> list = this.findAllByCustomerId(customerId);
        Optional<WishByCustomer> filtered = list.stream()
                .filter(wishByCustomer -> wishByCustomer.getProductId().equals(productId))
                .findFirst();
        return filtered;
    }
}
