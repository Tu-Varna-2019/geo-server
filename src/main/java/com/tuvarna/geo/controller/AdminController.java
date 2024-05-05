package com.tuvarna.geo.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tuvarna.geo.service.AdminService;
import com.tuvarna.geo.service.dto.RestApiResponse;
import com.tuvarna.geo.service.dto.user.request.LoggerDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private static final Logger logger = LogManager.getLogger(AdminController.class.getName());
    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("fetch/logs")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve user logs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Logs retrieved!"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<RestApiResponse<List<LoggerDTO>>> getLogs() {
        logger.info("Received a request from the admin to query user logs!");
        return new ResponseEntity<>(adminService.getLogs(), HttpStatus.OK);
    }

    @PostMapping("save/log")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Save log")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User blocked!"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<RestApiResponse<Void>> saveLog(@RequestBody LoggerDTO loggerDTO) {
        logger.info("Received a log to be saved later for the admins!: {}", loggerDTO);

        return new ResponseEntity<>(adminService.saveLog(loggerDTO), HttpStatus.OK);
    }

    @PutMapping("users/{email}/block/{blocked}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Block user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User blocked!"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<RestApiResponse<Void>> blockUser(
            @PathVariable("email") String email, @PathVariable("blocked") Boolean blocked) {
        logger.info("Received a request from the admin to block={}  a user: {}", blocked, email);

        return new ResponseEntity<>(adminService.block(email, blocked), HttpStatus.OK);
    }
}