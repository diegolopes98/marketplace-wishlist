package com.marketplace.wishlist.infrastructure.api.controllers;

import com.marketplace.wishlist.application.wishlist.add.AddInput;
import com.marketplace.wishlist.application.wishlist.add.AddOutput;
import com.marketplace.wishlist.application.wishlist.add.AddUseCase;
import com.marketplace.wishlist.application.wishlist.delete.DeleteInput;
import com.marketplace.wishlist.application.wishlist.delete.DeleteUseCase;
import com.marketplace.wishlist.application.wishlist.find.FindInput;
import com.marketplace.wishlist.application.wishlist.find.FindOutPut;
import com.marketplace.wishlist.application.wishlist.find.FindUseCase;
import com.marketplace.wishlist.application.wishlist.getAll.GetAllInput;
import com.marketplace.wishlist.application.wishlist.getAll.GetAllOutPut;
import com.marketplace.wishlist.application.wishlist.getAll.GetAllUseCase;
import com.marketplace.wishlist.infrastructure.models.ItemsRequest;
import com.marketplace.wishlist.infrastructure.models.ItemsResponse;
import com.marketplace.wishlist.infrastructure.models.WishlistResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/{customerId}/wishlist")
@Slf4j
public class WishlistController {
    private final AddUseCase addUseCase;
    private final DeleteUseCase deleteUseCase;

    private final FindUseCase findUseCase;

    private final GetAllUseCase getAllUseCase;

    @Autowired
    public WishlistController(
            AddUseCase addUseCase,
            DeleteUseCase deleteUseCase,
            FindUseCase findUseCase,
            GetAllUseCase getAllUseCase
    ) {
        this.addUseCase = addUseCase;
        this.deleteUseCase = deleteUseCase;
        this.findUseCase = findUseCase;
        this.getAllUseCase = getAllUseCase;
    }

    @PostMapping
    public ResponseEntity<ItemsResponse> add(
            @PathVariable final String customerId,
            @RequestBody final ItemsRequest body
    ) throws Exception {
        AddInput input = AddInput.with(
                UUID.fromString(customerId),
                UUID.fromString(body.productId()),
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
    ResponseEntity delete (
            @PathVariable final String customerId,
            @PathVariable final String productId
    ) throws Exception {
        DeleteInput input = DeleteInput.with(
                UUID.fromString(customerId),
                UUID.fromString(productId)
        );
        log.info("Deleting wish {} of customer's {} wishlist", productId, customerId);
        this.deleteUseCase.execute(input);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/{productId}")
    public ResponseEntity<ItemsResponse> find (
            @PathVariable final String customerId,
            @PathVariable final String productId
    ) throws Exception {
        FindInput input = FindInput.with(
                UUID.fromString(customerId),
                UUID.fromString(productId)
        );
        log.info("Getting wish {} of customer's {} wishlist", productId, customerId);
        FindOutPut output = this.findUseCase.execute(input);
        ItemsResponse response = new ItemsResponse(
                output.name(),
                output.description(),
                output.amount()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping()
    public ResponseEntity<WishlistResponse> getAll (
            @PathVariable final String customerId
    ) throws Exception {
        GetAllInput input = GetAllInput.with(
                UUID.fromString(customerId)
        );
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
