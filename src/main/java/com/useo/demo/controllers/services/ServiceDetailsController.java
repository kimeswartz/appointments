package com.useo.demo.controllers.services;

import com.useo.demo.dtos.services.ServiceDetailsDto;
import com.useo.demo.services.services.ServiceOfDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/edit")
@RestController
public class ServiceDetailsController {

    private final ServiceOfDetailsService serviceOfDetailsService;

    public ServiceDetailsController(ServiceOfDetailsService serviceOfDetailsService) {
        this.serviceOfDetailsService = serviceOfDetailsService;
    }

    // Create new service details
    @PostMapping("/details/upload")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<ServiceDetailsDto> createServiceDetails(@RequestBody ServiceDetailsDto serviceDetailsDto) {
        try {
            ServiceDetailsDto createdServiceDetailsDto = serviceOfDetailsService.createServiceDetails(serviceDetailsDto);
            return ResponseEntity.ok(createdServiceDetailsDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Update existing service details
    @PutMapping("/details/update/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<ServiceDetailsDto> updateServiceDetails(@PathVariable Long id, @RequestBody ServiceDetailsDto serviceDetailsDto) {
        try {
            ServiceDetailsDto updatedServiceDetailsDto = serviceOfDetailsService.updateServiceDetails(id, serviceDetailsDto);
            return ResponseEntity.ok(updatedServiceDetailsDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Get all service details
    @GetMapping("/details/list")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<List<ServiceDetailsDto>> getAllServiceDetails() {
        List<ServiceDetailsDto> serviceDetailsDtos = serviceOfDetailsService.findAll();
        return ResponseEntity.ok(serviceDetailsDtos);
    }

    // Get specific service details by ID
    @GetMapping("/details/single/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<ServiceDetailsDto> getServiceDetailsById(@PathVariable Long id) {
        try {
            ServiceDetailsDto serviceDetailsDto = serviceOfDetailsService.findById(id);
            return ResponseEntity.ok(serviceDetailsDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete specific service details by ID
    @DeleteMapping("/details/delete/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Void> deleteServiceDetails(@PathVariable Long id) {
        try {
            serviceOfDetailsService.deleteServiceDetails(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}