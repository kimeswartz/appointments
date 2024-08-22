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
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Retrieve all users
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    // Create a new user with 'user' role by default
    public User createUser(UserDto input) {
        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.USER);
        if (optionalRole.isEmpty()) {
            throw new RuntimeException("Role USER not found");
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

    // Update an existing user (regular users)
    public User updateUser(Long id, UserDto input) {
        Optional<User> existingUserOpt = userRepository.findById(id);
        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();
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

    // Retrieve user by ID
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    // Delete a regular user by ID
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}