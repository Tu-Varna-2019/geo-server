package com.tuvarna.geo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.tuvarna.geo.entity.User;
import com.tuvarna.geo.repository.UserRepository;

public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(User object) {
        userRepository.save(object);
    }
}
