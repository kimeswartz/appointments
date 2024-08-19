package com.useo.demo.controllers;

import com.useo.demo.dtos.StaffDto;
import com.useo.demo.entities.Staff;
import com.useo.demo.services.StaffService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/staff")
@RestController
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @PostMapping
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Staff> createStaff(@RequestBody StaffDto staffDto) {
        Staff createdStaff = staffService.createStaff(staffDto);
        return ResponseEntity.ok(createdStaff);
    }

    // Should be updated so admin also can see, also update endpoint so its not /add-staff/our-staff
    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<List<Staff>> findAllStaff() {
        List<Staff> staffList = staffService.findAll();
        return ResponseEntity.ok(staffList);
    }

}

