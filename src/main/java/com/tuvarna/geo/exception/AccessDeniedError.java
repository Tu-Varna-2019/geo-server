package com.tuvarna.geo.exception;

public class AccessDeniedError extends RuntimeException {
    public AccessDeniedError(String message) {
        super(message);
    }
}