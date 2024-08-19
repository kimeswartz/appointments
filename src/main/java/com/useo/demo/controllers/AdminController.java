package com.useo.demo.controllers;

import com.useo.demo.dtos.RegisterUserDto;
import com.useo.demo.entities.SaveUser;
import com.useo.demo.entities.Treatment;
import com.useo.demo.services.TreatmentService;
import com.useo.demo.services.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admins")
@RestController
public class AdminController {
    private final UserService userService;
    private final TreatmentService treatmentService;

    public AdminController(UserService userService, TreatmentService treatmentService) {
        this.userService = userService;
        this.treatmentService = treatmentService;
    }

    @PostMapping
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<SaveUser> createAdministrator(@RequestBody RegisterUserDto registerUserDto) {
        SaveUser createAdmin = userService.createAdministrator(registerUserDto);

        return ResponseEntity.ok(createAdmin);
    }

    @GetMapping("/treatments")
    public ResponseEntity<List<Treatment>> allTreatments() {
        List<Treatment> treatments = treatmentService.findAll();

        return ResponseEntity.ok(treatments);
    }

}
