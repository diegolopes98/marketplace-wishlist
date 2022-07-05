package com.marketplace.wishlist.application;

public abstract class InputUseCase<IN> {
    public abstract void execute(IN input) throws Exception;
}
