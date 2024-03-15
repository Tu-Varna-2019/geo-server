package com.tuvarna.geo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tuvarna.geo.entity.User;
import com.tuvarna.geo.service.UserService;
import com.tuvarna.geo.service.dto.UserDTO;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class RegisterController {

    private UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> create(@RequestBody UserDTO userDto) {

        User createdUser = userService.registerUser(userDto);
        return new ResponseEntity<>(String.format("User %s registered successfully", createdUser.getUsername()),
                HttpStatus.CREATED);

    }
}
