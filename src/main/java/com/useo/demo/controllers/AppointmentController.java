package com.useo.demo.controllers;

import com.useo.demo.dtos.AppointmentDto;
import com.useo.demo.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    // Create a new appointment
    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'MANAGER')")
    public ResponseEntity<AppointmentDto> createAppointment(@RequestBody AppointmentDto appointmentDto) {
        AppointmentDto createdAppointment = appointmentService.createAppointment(appointmentDto);
        return ResponseEntity.ok(createdAppointment);
    }

    // Update an existing appointment
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<AppointmentDto> updateAppointment(@PathVariable Long id, @RequestBody AppointmentDto appointmentDto) {
        AppointmentDto updatedAppointment = appointmentService.updateAppointment(id, appointmentDto);
        return ResponseEntity.ok(updatedAppointment);
    }

    // Retrieve all appointments
    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<List<AppointmentDto>> seeAllAppointments() {
        List<AppointmentDto> appointments = appointmentService.seeAllAppointments();
        return ResponseEntity.ok(appointments);
    }

    // Retrieve an appointment by ID
    @GetMapping("/single/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<AppointmentDto> getAppointmentById(@PathVariable Long id) {
        Optional<AppointmentDto> appointmentDto = appointmentService.getAppointmentById(id);
        return appointmentDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete an appointment by ID
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }

    // Find appointments by user ID
    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<List<AppointmentDto>> getAppointmentsByUserId(@PathVariable Long userId) {
        List<AppointmentDto> appointments = appointmentService.findAppointmentsByUserId(userId);
        return ResponseEntity.ok(appointments);
    }

    // Find appointments by staff ID
    @GetMapping("/staff/{staffId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<List<AppointmentDto>> getAppointmentsByStaffId(@PathVariable Long staffId) {
        List<AppointmentDto> appointments = appointmentService.findAppointmentsByStaffId(staffId);
        return ResponseEntity.ok(appointments);
    }

    // Find appointments by location ID
    @GetMapping("/location/{locationId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<List<AppointmentDto>> getAppointmentsByLocationId(@PathVariable Long locationId) {
        List<AppointmentDto> appointments = appointmentService.findAppointmentsByLocationId(locationId);
        return ResponseEntity.ok(appointments);
    }

    // Find appointments by service ID
    @GetMapping("/service/{serviceId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<List<AppointmentDto>> getAppointmentsByServiceId(@PathVariable Long serviceId) {
        List<AppointmentDto> appointments = appointmentService.findAppointmentsByServiceId(serviceId);
        return ResponseEntity.ok(appointments);
    }

    // Find appointments by confirmation status
    @GetMapping("/confirmed/{isConfirmed}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<List<AppointmentDto>> getAppointmentsByIsConfirmed(@PathVariable Boolean isConfirmed) {
        List<AppointmentDto> appointments = appointmentService.findAppointmentsByIsConfirmed(isConfirmed);
        return ResponseEntity.ok(appointments);
    }
}