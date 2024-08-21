package com.useo.demo.controllers;

import com.useo.demo.dtos.UserDto;
import com.useo.demo.entities.User;
import com.useo.demo.entities.services.ServiceName;
import com.useo.demo.services.services.ServiceOfNameService;
import com.useo.demo.services.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admins")
@RestController
public class AdminController {
    private final UserService userService;
    private final ServiceOfNameService serviceOfNameService;

    public AdminController(UserService userService, ServiceOfNameService serviceOfNameService) {
        this.userService = userService;
        this.serviceOfNameService = serviceOfNameService;
    }

    @PostMapping
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<User> createAdministrator(@RequestBody UserDto userDto) {
        User createAdmin = userService.createAdministrator(userDto);

        return ResponseEntity.ok(createAdmin);
    }

    @GetMapping("/treatments")
    public ResponseEntity<List<ServiceName>> allTreatments() {
        List<ServiceName> serviceNames = serviceOfNameService.findAll();

        return ResponseEntity.ok(serviceNames);
    }

}
