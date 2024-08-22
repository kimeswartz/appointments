package com.useo.demo.services;

import com.useo.demo.dtos.UserDto;
import com.useo.demo.entities.Role;
import com.useo.demo.entities.RoleEnum;
import com.useo.demo.entities.User;
import com.useo.demo.repositories.RoleRepository;
import com.useo.demo.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Create a new user with ADMIN role
    public User createAdministrator(UserDto input) {
        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.ADMIN);
        if (optionalRole.isEmpty()) {
            throw new RuntimeException("Role ADMIN not found");
        }

        var user = new User()
                .setName(input.getName())
                .setSurname(input.getSurname())
                .setEmail(input.getEmail())
                .setPhoneNumber(input.getPhoneNumber())
                .setPassword(passwordEncoder.encode(input.getPassword()))
                .setRole(optionalRole.get());

        return userRepository.save(user);
    }

    // Update an existing admin user (only by MANAGER)
    public User updateAdministrator(Long id, UserDto input) {
        Optional<User> existingUserOpt = userRepository.findById(id);
        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();
            if (!existingUser.getRole().getName().equals(RoleEnum.ADMIN)) {
                throw new RuntimeException("User with id: " + id + " is not an ADMIN");
            }
            existingUser.setName(input.getName())
                    .setSurname(input.getSurname())
                    .setEmail(input.getEmail())
                    .setPhoneNumber(input.getPhoneNumber());

            // Password update check
            if (input.getPassword() != null && !input.getPassword().isEmpty()) {
                existingUser.setPassword(passwordEncoder.encode(input.getPassword()));
            }

            // Role is not updated
            return userRepository.save(existingUser);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    // Delete an ADMIN user by ID
    public void deleteAdministrator(Long id) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getRole().getName().equals(RoleEnum.ADMIN)) {
                userRepository.deleteById(id);
            } else {
                throw new RuntimeException("User with id: " + id + " is not an ADMIN");
            }
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    // Find all ADMIN users
    public List<User> findAllAdmins() {
        List<User> admins = new ArrayList<>();
        Role adminRole = roleRepository.findByName(RoleEnum.ADMIN)
                .orElseThrow(() -> new RuntimeException("Role ADMIN not found"));

        userRepository.findAll().forEach(user -> {
            if (user.getRole().equals(adminRole)) {
                admins.add(user);
            }
        });
        return admins;
    }
}