package com.exam.instanttest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exam.instanttest.service.ParkingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mashape.unirest.http.exceptions.UnirestException;

@RestController
@RequestMapping("/api/parking")
public class ParkingController {
    @Autowired
    private ParkingService parkingService;

    @GetMapping("/")
    public ResponseEntity<?> getParkings(
            @RequestParam(name = "cityName", defaultValue = "", required = false) String cityName,
            @RequestParam(name = "longitude", required = true) Double longitude,
            @RequestParam(name = "latitude", required = true) Double latitude)
            throws UnirestException, JsonMappingException, JsonProcessingException {
        return ResponseEntity.ok().body(parkingService.getParkingSlots(longitude, latitude));
    }
}
