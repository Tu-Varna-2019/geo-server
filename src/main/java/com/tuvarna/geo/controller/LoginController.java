// package com.tuvarna.geo.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import com.tuvarna.geo.repository.UserRepositoryImpl;

// import io.swagger.v3.oas.annotations.parameters.RequestBody;

// @RestController
// @RequestMapping("/login")
// public class LoginController {

// @Autowired
// private UserRepositoryImpl userRepository;

// @SuppressWarnings("rawtypes")
// @GetMapping
// public Iterable findAll() {
// return userRepository.findAll();
// }

// @PostMapping("/verify")
// public void verify(@RequestBody String email, @RequestBody String password) {
// userRepository.verify(email, password);
// }

// }
