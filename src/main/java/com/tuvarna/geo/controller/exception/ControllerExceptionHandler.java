package com.tuvarna.geo.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.tuvarna.geo.exception.AccessDeniedError;
import com.tuvarna.geo.exception.BadRequestError;
import com.tuvarna.geo.exception.ForbiddenError;
import com.tuvarna.geo.exception.ResourceNotFoundError;
import com.tuvarna.geo.service.dto.RestApiResponse;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ForbiddenError.class)
    public ResponseEntity<RestApiResponse<Void>> handleForbiddenError(ForbiddenError ex,
            WebRequest request) {
        RestApiResponse<Void> apiResponse = new RestApiResponse<>(null, ex.getMessage(), HttpStatus.FORBIDDEN.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ResourceNotFoundError.class)
    public ResponseEntity<RestApiResponse<Void>> handleResourceNotFoundError(ResourceNotFoundError ex,
            WebRequest request) {
        RestApiResponse<Void> apiResponse = new RestApiResponse<>(null, ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestError.class)
    public ResponseEntity<RestApiResponse<Void>> handleBadRequestError(BadRequestError ex, WebRequest request) {

        RestApiResponse<Void> apiResponse = new RestApiResponse<>(null, ex.getMessage(),
                HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<RestApiResponse<Void>> handleNoResourceFoundException(NoResourceFoundException ex,
            WebRequest request) {

        RestApiResponse<Void> apiResponse = new RestApiResponse<>(null, ex.getMessage(),
                HttpStatus.I_AM_A_TEAPOT.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.I_AM_A_TEAPOT);
    }

    @ExceptionHandler(AccessDeniedError.class)
    public ResponseEntity<RestApiResponse<Void>> handleAccessDeniedError(AccessDeniedError ex, WebRequest request) {
        RestApiResponse<Void> apiResponse = new RestApiResponse<>(null, ex.getMessage(), HttpStatus.FORBIDDEN.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestApiResponse<Void>> handleGlobalException(Exception ex, WebRequest request) {
        RestApiResponse<Void> apiResponse = new RestApiResponse<>(null, "An error occurred",
                HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}