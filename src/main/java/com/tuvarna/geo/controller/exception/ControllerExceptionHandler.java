package com.tuvarna.geo.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import java.util.Date;

import com.tuvarna.geo.exception.BadRequestError;
import com.tuvarna.geo.exception.ResourceNotFoundError;
import com.tuvarna.geo.exception.ServerErrorMessage;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundError.class)
    public ResponseEntity<?> handleResourceNotFoundError(ResourceNotFoundError ex, WebRequest request) {

        ServerErrorMessage serverErrorMessage = new ServerErrorMessage(new Date(),
                "Error occured: Not found!", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(serverErrorMessage);
    }

    @ExceptionHandler(BadRequestError.class)
    public ResponseEntity<?> handleBadRequestError(BadRequestError ex, WebRequest request) {
        ServerErrorMessage serverErrorMessage = new ServerErrorMessage(new Date(),
                "Bad Request!", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(serverErrorMessage);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
        ServerErrorMessage serverErrorMessage = new ServerErrorMessage(new Date(),
                "Error occured: Sorry for the inconvenience!", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(serverErrorMessage);
    }
}