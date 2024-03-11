package com.tuvarna.geo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tuvarna.geo.entity.User;
import com.tuvarna.geo.repository.UserRepository;
import com.tuvarna.geo.repository.UserTypeRepository;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTypeRepository userTypeRepository;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> create(@RequestBody User user) {
        String bodyResponse = "User registered successfully";
        HttpStatus httpStatus = HttpStatus.CREATED;

        try {

            // Set the user type
            user.setUserType(
                    userTypeRepository.findByType(user.getType()));

            userRepository.save(user);
        } catch (Exception e) {

            e.printStackTrace();
            bodyResponse = "User registration failed";
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        }
        return new ResponseEntity<>(bodyResponse, httpStatus);
    }
}
