package com.marketplace.wishlist.infrastructure.api;

import com.marketplace.wishlist.application.add.AddInput;
import com.marketplace.wishlist.application.add.AddOutput;
import com.marketplace.wishlist.application.add.AddUseCase;
import com.marketplace.wishlist.application.delete.DeleteInput;
import com.marketplace.wishlist.application.delete.DeleteUseCase;
import com.marketplace.wishlist.application.exceptions.NotFoundException;
import com.marketplace.wishlist.application.get.GetInput;
import com.marketplace.wishlist.application.get.GetOutPut;
import com.marketplace.wishlist.application.get.GetUseCase;
import com.marketplace.wishlist.application.getAll.GetAllInput;
import com.marketplace.wishlist.application.getAll.GetAllOutPut;
import com.marketplace.wishlist.application.getAll.GetAllUseCase;
import com.marketplace.wishlist.domain.exceptions.BadUUIDException;
import com.marketplace.wishlist.domain.exceptions.MaxLimitExceededException;
import com.marketplace.wishlist.infrastructure.models.ItemsRequest;
import com.marketplace.wishlist.infrastructure.models.ItemsResponse;
import com.marketplace.wishlist.infrastructure.models.WishlistResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/{customerId}/wishlist")
@Slf4j
public class WishlistAPI {
    private final AddUseCase addUseCase;
    private final DeleteUseCase deleteUseCase;

    private final GetUseCase getUseCase;

    private final GetAllUseCase getAllUseCase;

    @Autowired
    public WishlistAPI(
            AddUseCase addUseCase,
            DeleteUseCase deleteUseCase,
            GetUseCase getUseCase,
            GetAllUseCase getAllUseCase
    ) {
        this.addUseCase = addUseCase;
        this.deleteUseCase = deleteUseCase;
        this.getUseCase = getUseCase;
        this.getAllUseCase = getAllUseCase;
    }

    @PostMapping
    public ResponseEntity<ItemsResponse> post(
            @PathVariable final String customerId,
            @RequestBody final ItemsRequest body
    ) throws MaxLimitExceededException, BadUUIDException {
        AddInput input = AddInput.with(
                customerId,
                body.productId(),
                body.name(),
                body.description(),
                body.amount()
        );
        log.info("Creating wish {} into customer's {} wishlist", body, customerId);
        AddOutput output = addUseCase.execute(input);
        ItemsResponse response = new ItemsResponse(output.name(), output.description(), output.amount());
        return ResponseEntity
                .created(URI.create(customerId + "/wishlist/" + body.productId()))
                .body(response);
    }

    @DeleteMapping(path = "/{productId}")
    ResponseEntity delete(
            @PathVariable final String customerId,
            @PathVariable final String productId
    ) throws NotFoundException, BadUUIDException {
        DeleteInput input = DeleteInput.with(
                customerId,
                productId
        );
        log.info("Deleting wish {} of customer's {} wishlist", productId, customerId);
        this.deleteUseCase.execute(input);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/{productId}")
    public ResponseEntity<ItemsResponse> get(
            @PathVariable final String customerId,
            @PathVariable final String productId
    ) throws Exception {
        GetInput input = GetInput.with(
                customerId,
                productId
        );
        log.info("Getting wish {} of customer's {} wishlist", productId, customerId);
        GetOutPut output = this.getUseCase.execute(input);
        ItemsResponse response = new ItemsResponse(
                output.name(),
                output.description(),
                output.amount()
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping()
    public ResponseEntity<WishlistResponse> getAll(
            @PathVariable final String customerId
    ) throws Exception {
        GetAllInput input = GetAllInput.with(customerId);
        log.info("Getting all wishes of customer's {} wishlist", customerId);
        List<GetAllOutPut> outputList = this.getAllUseCase.execute(input);
        List<ItemsResponse> outputMapped = outputList
                .stream()
                .map(
                        output -> new ItemsResponse(
                                output.name(),
                                output.description(),
                                output.amount()
                        ))
                .collect(Collectors.toList());
        WishlistResponse wishlistResponse = new WishlistResponse(outputMapped);
        return ResponseEntity.ok(wishlistResponse);
    }
}
