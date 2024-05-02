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

import com.tuvarna.geo.entity.Earthquake;
import com.tuvarna.geo.entity.Soil;
import com.tuvarna.geo.service.EarthquakeService;
import com.tuvarna.geo.service.SoilService;
import com.tuvarna.geo.service.dto.RestApiResponse;
import com.tuvarna.geo.service.dto.danger.DangerDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/danger")
public class DangerController {

    private static final Logger logger = LogManager.getLogger(DangerController.class.getName());
    private SoilService soilService;
    private EarthquakeService earthquakeService;

    @Autowired
    public DangerController(SoilService soilService, EarthquakeService earthquakeService) {
        this.soilService = soilService;
        this.earthquakeService = earthquakeService;
    }

    @PostMapping("/soil")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve soil type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Soil type retrieved!"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<RestApiResponse<Soil>> getSoil(@RequestBody DangerDTO dangerDTO) {
        logger.info("Received a polypoint from client to get the corresponding soil type: {}", dangerDTO);

        return new ResponseEntity<>(soilService.getSoil(dangerDTO), HttpStatus.OK);
    }

    @PostMapping("/earthquake")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve earthquake")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Earthquake retrieved!"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<RestApiResponse<Earthquake>> getEarthquake(@RequestBody DangerDTO dangerDTO) {
        logger.info("Received a polypoint from client to get the corresponding earthquake: {}", dangerDTO);

        return new ResponseEntity<>(earthquakeService.getEarthquake(dangerDTO), HttpStatus.OK);
    }
}