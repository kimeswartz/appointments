package com.useo.demo.controllers;

import com.useo.demo.dtos.StaffDto;
import com.useo.demo.entities.Staff;
import com.useo.demo.services.StaffService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/staff")
@RestController
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Staff> createStaff(@RequestBody StaffDto staffDto) {
        Staff createdStaff = staffService.createStaff(staffDto);
        return ResponseEntity.ok(createdStaff);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Staff> updateStaff(@PathVariable Long id, @RequestBody StaffDto staffDto) {
        try {
            Staff updatedStaff = staffService.updateStaff(id, staffDto);
            return ResponseEntity.ok(updatedStaff);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<List<Staff>> findAllStaff() {
        List<Staff> staffList = staffService.findAll();
        return ResponseEntity.ok(staffList);
    }

    @GetMapping("/single/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<Staff> findStaffById(@PathVariable Long id) {
        Optional<Staff> staff = staffService.findById(id);
        return staff.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Void> deleteStaff(@PathVariable Long id) {
        staffService.deleteStaff(id);
        return ResponseEntity.noContent().build();
    }
}

