package com.useo.demo.controllers.services;

import com.useo.demo.dtos.services.ServiceDto;
import com.useo.demo.dtos.services.ServiceNameDto;
import com.useo.demo.services.services.ServiceNameOfService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/edit")
@RestController
public class ServiceController {

    private final ServiceNameOfService serviceNameOfService;

    public ServiceController(ServiceNameOfService serviceNameOfService) {
        this.serviceNameOfService = serviceNameOfService;
    }

    // Create a new service
    @PostMapping("/service/upload")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<ServiceNameDto> createService(@RequestBody ServiceDto serviceDto) {
        try {
            ServiceNameDto createdServiceNameDto = serviceNameOfService.createService(serviceDto);
            return ResponseEntity.ok(createdServiceNameDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Update an existing service
    @PutMapping("/service/update/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<ServiceNameDto> updateService(@PathVariable Long id, @RequestBody ServiceDto serviceDto) {
        if (!serviceNameOfService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        try {
            ServiceNameDto updatedServiceNameDto = serviceNameOfService.updateService(id, serviceDto);
            return ResponseEntity.ok(updatedServiceNameDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Get all services
    @GetMapping("/service/list")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<List<ServiceNameDto>> getAllServices() {
        List<ServiceNameDto> serviceNameDtos = serviceNameOfService.findAll();
        return ResponseEntity.ok(serviceNameDtos);
    }

    // Get a specific service by ID
    @GetMapping("/service/type/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<ServiceNameDto> getServiceById(@PathVariable Long id) {
        if (!serviceNameOfService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        try {
            ServiceNameDto serviceNameDto = serviceNameOfService.findById(id);
            return ResponseEntity.ok(serviceNameDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a specific service by ID
    @DeleteMapping("/service/delete/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        if (!serviceNameOfService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        serviceNameOfService.deleteService(id);
        return ResponseEntity.noContent().build();
    }
}
