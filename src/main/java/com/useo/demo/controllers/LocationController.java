package com.useo.demo.controllers;

import com.useo.demo.dtos.LocationDto;
import com.useo.demo.entities.Location;
import com.useo.demo.services.LocationService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/locations")
@RestController
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Location> createLocation(@RequestBody LocationDto locationDto) {
        Location createdLocation = locationService.createLocation(locationDto);
        return ResponseEntity.ok(createdLocation);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<List<Location>> findAllLocations() {
        List<Location> locationList = locationService.findAll();
        return ResponseEntity.ok(locationList);
    }
}
