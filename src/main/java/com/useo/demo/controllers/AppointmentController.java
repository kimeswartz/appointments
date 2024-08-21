package com.useo.demo.controllers;

import com.useo.demo.dtos.AppointmentDto;
import com.useo.demo.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    // Create or update an appointment
    @PostMapping
    public ResponseEntity<AppointmentDto> createOrUpdateAppointment(@RequestBody AppointmentDto appointmentDto) {
        AppointmentDto savedAppointment = appointmentService.saveAppointment(appointmentDto);
        return ResponseEntity.ok(savedAppointment);
    }

    // Find an appointment by ID
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDto> getAppointmentById(@PathVariable Long id) {
        Optional<AppointmentDto> appointmentDto = appointmentService.findAppointmentById(id);
        return appointmentDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Find appointments by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AppointmentDto>> getAppointmentsByUserId(@PathVariable Long userId) {
        List<AppointmentDto> appointments = appointmentService.findAppointmentsByUserId(userId);
        return ResponseEntity.ok(appointments);
    }

    // Find appointments by staff ID
    @GetMapping("/staff/{staffId}")
    public ResponseEntity<List<AppointmentDto>> getAppointmentsByStaffId(@PathVariable Long staffId) {
        List<AppointmentDto> appointments = appointmentService.findAppointmentsByStaffId(staffId);
        return ResponseEntity.ok(appointments);
    }

    // Find appointments by location ID
    @GetMapping("/location/{locationId}")
    public ResponseEntity<List<AppointmentDto>> getAppointmentsByLocationId(@PathVariable Long locationId) {
        List<AppointmentDto> appointments = appointmentService.findAppointmentsByLocationId(locationId);
        return ResponseEntity.ok(appointments);
    }

    // Find appointments by service ID
    @GetMapping("/service/{serviceId}")
    public ResponseEntity<List<AppointmentDto>> getAppointmentsByServiceId(@PathVariable Long serviceId) {
        List<AppointmentDto> appointments = appointmentService.findAppointmentsByServiceId(serviceId);
        return ResponseEntity.ok(appointments);
    }

    // Find appointments by confirmation status
    @GetMapping("/confirmed/{isConfirmed}")
    public ResponseEntity<List<AppointmentDto>> getAppointmentsByIsConfirmed(@PathVariable Boolean isConfirmed) {
        List<AppointmentDto> appointments = appointmentService.findAppointmentsByIsConfirmed(isConfirmed);
        return ResponseEntity.ok(appointments);
    }
}