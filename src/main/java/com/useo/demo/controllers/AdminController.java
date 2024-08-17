package com.useo.demo.controllers;

import com.useo.demo.dtos.RegisterUserDto;
import com.useo.demo.entities.SaveUser;
import com.useo.demo.services.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admins")
@RestController
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<SaveUser> createAdministrator(@RequestBody RegisterUserDto registerUserDto) {
        SaveUser createAdmin = userService.createAdministrator(registerUserDto);

        return ResponseEntity.ok(createAdmin);
    }

}
