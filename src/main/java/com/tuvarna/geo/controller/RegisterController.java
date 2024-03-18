package com.tuvarna.geo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tuvarna.geo.entity.User;
import com.tuvarna.geo.service.UserService;
import com.tuvarna.geo.service.dto.UserDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class RegisterController {

    private static final Logger logger = LogManager.getLogger(RegisterController.class.getName());

    private UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<String> create(@RequestBody UserDTO userDto) {

        logger.info("Received a request from client to register user: {}", userDto);

        User createdUser = userService.registerUser(userDto);
        logger.info("User %s registered successfully", createdUser.getEmail());

        return new ResponseEntity<>(String.format("User %s registered successfully", createdUser.getUsername()),
                HttpStatus.CREATED);

    }
}
