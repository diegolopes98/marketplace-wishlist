package com.marketplace.wishlist.application.getAll;

import com.marketplace.wishlist.domain.Wish;
import com.marketplace.wishlist.domain.WishID;
import com.marketplace.wishlist.domain.WishlistGateway;
import com.marketplace.wishlist.domain.exceptions.BadUUIDException;

import java.util.List;
import java.util.stream.Collectors;

public class GetAllUseCase {

    private WishlistGateway gateway;

    public GetAllUseCase(WishlistGateway gateway) {
        this.gateway = gateway;
    }

    public List<GetAllOutPut> execute(GetAllInput input) throws BadUUIDException {
        WishID customerId = WishID.from(input.customerId());
        List<Wish> wishes = this.gateway.getAllByCustomerId(customerId);
        return wishes
                .stream()
                .map(wish -> GetAllOutPut.from(wish))
                .collect(Collectors.toList());
    }
}
