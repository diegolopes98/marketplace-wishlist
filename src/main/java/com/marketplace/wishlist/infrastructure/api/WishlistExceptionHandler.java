package com.marketplace.wishlist.infrastructure.api;

import com.marketplace.wishlist.application.exceptions.NotFoundException;
import com.marketplace.wishlist.domain.exceptions.BadUUIDException;
import com.marketplace.wishlist.domain.exceptions.MaxLimitExceededException;
import com.marketplace.wishlist.infrastructure.models.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WishlistExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(final NotFoundException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponse response = new ErrorResponse(
                e.getMessage(),
                status.value()
        );
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(value = BadUUIDException.class)
    public ResponseEntity<ErrorResponse> handleBadUUID(final BadUUIDException e) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ErrorResponse response = new ErrorResponse(
                e.getMessage(),
                status.value()
        );
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(value = MaxLimitExceededException.class)
    public ResponseEntity<ErrorResponse> handleMaxLimit(final MaxLimitExceededException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse response = new ErrorResponse(
                e.getMessage(),
                status.value()
        );
        return ResponseEntity.status(status).body(response);
    }
}
