package com.marketplace.wishlist.utils;

import org.springframework.http.HttpStatus;

public class HttpResponse {
    private HttpStatus Status;
    private String body;

    public HttpStatus getStatus() {
        return Status;
    }

    public void setStatus(HttpStatus status) {
        Status = status;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
