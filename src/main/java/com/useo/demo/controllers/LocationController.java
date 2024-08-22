package com.useo.demo.controllers;

import com.useo.demo.dtos.LocationDto;
import com.useo.demo.entities.Location;
import com.useo.demo.services.LocationService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/locations")
@RestController
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Location> createLocation(@RequestBody LocationDto locationDto) {
        Location createdLocation = locationService.createLocation(locationDto);
        return ResponseEntity.ok(createdLocation);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Location> updateLocation(@PathVariable Long id, @RequestBody LocationDto locationDto) {
        try {
            Location updatedLocation = locationService.updateLocation(id, locationDto);
            return ResponseEntity.ok(updatedLocation);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<List<Location>> findAllLocations() {
        List<Location> locationList = locationService.findAll();
        return ResponseEntity.ok(locationList);
    }

    @GetMapping("/single/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Location> findLocationById(@PathVariable Long id) {
        Optional<Location> location = locationService.findById(id);
        return location.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
        locationService.deleteLocation(id);
        return ResponseEntity.noContent().build();
    }
}
