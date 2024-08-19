package com.useo.demo.controllers;

// Class to return the authenticated user from the JWT token for endpoints /user and users/me/

import com.useo.demo.entities.SaveUser;
import com.useo.demo.services.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/users")
@PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<SaveUser> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        SaveUser currentUser = (SaveUser) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("/")
    public ResponseEntity<List<SaveUser>> allUsers() {
        List<SaveUser> users = userService.findAll();

        return ResponseEntity.ok(users);
    }

}