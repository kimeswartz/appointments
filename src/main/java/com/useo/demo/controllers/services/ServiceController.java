package com.useo.demo.controllers.services;

import com.useo.demo.dtos.services.ServiceDto;
import com.useo.demo.entities.services.ServiceName;
import com.useo.demo.services.services.ServiceOfNameService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/edit")
@RestController
public class ServiceController {

    private final ServiceOfNameService serviceOfNameService;

    public ServiceController(ServiceOfNameService serviceOfNameService) {
        this.serviceOfNameService = serviceOfNameService;
    }

    // Create a new service
    @PostMapping("/service")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<ServiceName> createService(@RequestBody ServiceDto serviceDto) {
        ServiceName serviceName = serviceOfNameService.createService(serviceDto);
        return ResponseEntity.ok(serviceName);
    }

    // Update an existing service
    @PutMapping("/service/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<ServiceName> updateService(@PathVariable Long id, @RequestBody ServiceDto serviceDto) {
        if (!serviceOfNameService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        ServiceName updatedService = serviceOfNameService.updateService(id, serviceDto);
        return ResponseEntity.ok(updatedService);
    }

    // Get all services
    @GetMapping("/service")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<List<ServiceName>> getAllServices() {
        List<ServiceName> services = serviceOfNameService.findAll();
        return ResponseEntity.ok(services);
    }

    // Get a specific service by ID
    @GetMapping("/service/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<ServiceName> getServiceById(@PathVariable Long id) {
        if (!serviceOfNameService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        ServiceName serviceName = serviceOfNameService.findById(id);
        return ResponseEntity.ok(serviceName);
    }

    // Delete a specific service by ID
    @DeleteMapping("/service/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        if (!serviceOfNameService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        serviceOfNameService.deleteService(id);
        return ResponseEntity.noContent().build();
    }
}
