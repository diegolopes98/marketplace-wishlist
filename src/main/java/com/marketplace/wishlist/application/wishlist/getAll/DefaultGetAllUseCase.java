package com.marketplace.wishlist.application.wishlist.getAll;

import com.marketplace.wishlist.domain.WishID;
import com.marketplace.wishlist.domain.Wishlist;
import com.marketplace.wishlist.domain.WishlistGateway;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultGetAllUseCase extends GetAllUseCase {

    private WishlistGateway gateway;

    public DefaultGetAllUseCase(WishlistGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public List<GetAllOutPut> execute(GetAllInput input) throws Exception {
        Wishlist wishlist = this.gateway.getAll(WishID.from(input.customerId()));
        return wishlist
                .getItems()
                .stream()
                .map((wish -> GetAllOutPut.with(
                        wish.getName(),
                        wish.getDescription(),
                        wish.getAmount()
                )))
                .collect(Collectors.toList());
    }
}
