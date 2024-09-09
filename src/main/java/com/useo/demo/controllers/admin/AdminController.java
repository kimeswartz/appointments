package com.useo.demo.controllers.admin;

import com.useo.demo.dtos.user.UserResponseDto;
import com.useo.demo.dtos.user.UserSaveRequestDto;
import com.useo.demo.entities.user.User;

import com.useo.demo.services.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/current-admin")
    public ResponseEntity<UserResponseDto> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        UserResponseDto userResponseDto = userService.convertToResponseDto(currentUser);
        return ResponseEntity.ok(userResponseDto);
    }

    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    public ResponseEntity<List<UserResponseDto>> allUsers() {
        List<UserResponseDto> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserSaveRequestDto userSaveRequestDto) {
        UserResponseDto createdUser = userService.createUser(userSaveRequestDto);
        return ResponseEntity.ok(createdUser);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody UserSaveRequestDto userSaveRequestDto) {
        try {
            UserResponseDto updatedUser = userService.updateUser(id, userSaveRequestDto);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/single/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable Long id) {
        Optional<UserResponseDto> user = userService.findById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}