package com.tuvarna.geo.exception;

public class ForbiddenError extends RuntimeException {
    public ForbiddenError(String message) {
        super(message);
    }
}
