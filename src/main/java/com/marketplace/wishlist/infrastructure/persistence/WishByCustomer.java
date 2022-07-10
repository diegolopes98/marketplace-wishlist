package com.marketplace.wishlist.infrastructure.persistence;

import com.marketplace.wishlist.domain.Wish;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(WishByCustomer.TABLE_NAME)
public class WishByCustomer {
    public static final String TABLE_NAME = "wish_by_customer";
    @PrimaryKeyColumn(
            name = "customer_id",
            ordinal = 1,
            type = PrimaryKeyType.CLUSTERED,
            ordering = Ordering.DESCENDING
    )
    private UUID customerId;
    @PrimaryKeyColumn(
            name = "product_id",
            ordinal = 0,
            type = PrimaryKeyType.PARTITIONED
    )
    private UUID productId;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private Integer amount;

    public static WishByCustomer from(Wish wish) {
        return new WishByCustomer(
                wish.getCustomerId().getUUIDValue(),
                wish.getProductId().getUUIDValue(),
                wish.getName(),
                wish.getDescription(),
                wish.getAmount()
        );
    }
}