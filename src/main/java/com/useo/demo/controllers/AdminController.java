package com.useo.demo.controllers;

import com.useo.demo.dtos.UserDto;
import com.useo.demo.entities.User;
import com.useo.demo.services.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<User> createAdministrator(@RequestBody UserDto userDto) {
        User createdUser = adminService.createAdministrator(userDto);
        return ResponseEntity.ok(createdUser);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<User> updateAdministrator(@PathVariable Long id, @RequestBody UserDto userDto) {
        try {
            User updatedAdmin = adminService.updateAdministrator(id, userDto);
            return ResponseEntity.ok(updatedAdmin);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<List<User>> findAllAdmins() {
        List<User> adminList = adminService.findAllAdmins();
        return ResponseEntity.ok(adminList);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Void> deleteAdministrator(@PathVariable Long id) {
        try {
            adminService.deleteAdministrator(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
