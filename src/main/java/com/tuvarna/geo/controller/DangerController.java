package com.tuvarna.geo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tuvarna.geo.service.UserService;
import com.tuvarna.geo.service.dto.RestApiResponse;
import com.tuvarna.geo.service.dto.user.LoggedInUserDTO;
import com.tuvarna.geo.service.dto.user.LoginUserDTO;
import com.tuvarna.geo.service.dto.user.RegisterUserDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/danger")
public class DangerController {

    private static final Logger logger = LogManager.getLogger(DangerController.class.getName());
    private UserService userService;

    @Autowired
    public DangerController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/soil")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve soil type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Soil type retrieved!"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<RestApiResponse<LoggedInUserDTO>> login(@RequestBody LoginUserDTO userDto) {
        logger.info("Received a polypoint from client to get the corresponding soil type: {}", userDto);

        return new ResponseEntity<>(userService.login(userDto), HttpStatus.OK);
    }

    @PostMapping("/earthquake")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Retrieve earthquake")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<RestApiResponse<Void>> create(@RequestBody RegisterUserDTO userDto) {
        logger.info("Received a request from client to register user: {}", userDto);

        return new ResponseEntity<>(userService.registerUser(userDto), HttpStatus.OK);
    }
}