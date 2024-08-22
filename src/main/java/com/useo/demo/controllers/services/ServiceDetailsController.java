package com.useo.demo.controllers.services;

import com.useo.demo.dtos.services.ServiceDetailsDto;
import com.useo.demo.entities.services.ServiceDetails;
import com.useo.demo.services.services.ServiceDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/edit")
@RestController
public class ServiceDetailsController {

    private final ServiceDetailsService serviceDetailsService;

    public ServiceDetailsController(ServiceDetailsService serviceDetailsService) {
        this.serviceDetailsService = serviceDetailsService;
    }

    // Create a new service detail
    @PostMapping("/service-details/upload")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<ServiceDetails> createServiceDetails(@RequestBody ServiceDetailsDto serviceDetailsDto) {
        try {
            ServiceDetails serviceDetails = serviceDetailsService.createServiceDetails(serviceDetailsDto);
            return ResponseEntity.ok(serviceDetails);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Update an existing service detail
    @PutMapping("/service-details/update/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<ServiceDetails> updateServiceDetails(@PathVariable Long id, @RequestBody ServiceDetailsDto serviceDetailsDto) {
        try {
            ServiceDetails updatedServiceDetails = serviceDetailsService.updateServiceDetails(id, serviceDetailsDto);
            return ResponseEntity.ok(updatedServiceDetails);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Get all service details
    @GetMapping("/service-details/list")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<List<ServiceDetails>> getAllServiceDetails() {
        List<ServiceDetails> serviceDetails = serviceDetailsService.findAll();
        return ResponseEntity.ok(serviceDetails);
    }

    // Get a specific service detail by ID
    @GetMapping("/service-details/single/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<ServiceDetails> getServiceDetailsById(@PathVariable Long id) {
        try {
            ServiceDetails serviceDetails = serviceDetailsService.findById(id);
            return ResponseEntity.ok(serviceDetails);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a specific service detail by ID
    @DeleteMapping("/service-details/delete/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Void> deleteServiceDetails(@PathVariable Long id) {
        try {
            serviceDetailsService.deleteServiceDetails(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}