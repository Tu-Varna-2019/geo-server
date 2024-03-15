package com.tuvarna.geo.exception;

public class ResourceNotFoundError extends RuntimeException {
    public ResourceNotFoundError(String message) {
        super(message);
    }

    public ResourceNotFoundError(String message, Throwable cause) {
        super(message, cause);
    }
}