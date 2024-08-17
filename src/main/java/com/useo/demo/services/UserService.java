package com.useo.demo.services;

/*
  Service layer that provides logic for managing User entities
  The layer acts as an intermediary between the controller and the UserRepository
 */

import com.useo.demo.dtos.RegisterUserDto;
import com.useo.demo.entities.Role;
import com.useo.demo.entities.RoleEnum;

import com.useo.demo.entities.SaveUser;
import com.useo.demo.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.useo.demo.repositories.RoleRepository;

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

    public List<SaveUser> findAll() {
        List<SaveUser> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);

        return users;
    }

    public SaveUser createAdministrator(RegisterUserDto input) {
        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.ADMIN);

        if (optionalRole.isEmpty()) {
            return null;
        }

        var user = new SaveUser()
                .setFullName(input.getFullName())
                .setEmail(input.getEmail())
                .setPassword(passwordEncoder.encode(input.getPassword()))
                .setRole(optionalRole.get());

        return userRepository.save(user);
    }

}