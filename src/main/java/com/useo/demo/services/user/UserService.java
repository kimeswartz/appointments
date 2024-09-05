package com.useo.demo.services.user;

import com.useo.demo.dtos.user.UserSaveRequestDto;
import com.useo.demo.dtos.user.UserResponseDto;
import com.useo.demo.entities.role.Role;
import com.useo.demo.entities.role.RoleEnum;
import com.useo.demo.entities.user.User;
import com.useo.demo.repositories.role.RoleRepository;
import com.useo.demo.repositories.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    // Create Operation
    public UserResponseDto createUser(UserSaveRequestDto input) {
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

        User savedUser = userRepository.save(user);
        return convertToResponseDto(savedUser);
    }

    // Update Operation
    public UserResponseDto updateUser(Long id, UserSaveRequestDto input) {
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
            User updatedUser = userRepository.save(existingUser);
            return convertToResponseDto(updatedUser);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    // Retrieve user by ID
    public Optional<UserResponseDto> findById(Long id) {
        Optional<User> userOpt = userRepository.findById(id);
        return userOpt.map(this::convertToResponseDto);
    }

    // Retrieve all users
    public List<UserResponseDto> findAll() {
        List<User> users = (List<User>) userRepository.findAll();
        return users.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    // Delete Operation
    @Transactional
    public void deleteUser(Long id) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            User user = userOpt.get();

            user.setRole(null); // Remove association
            userRepository.save(user); // Save changes
            userRepository.delete(user); // Then delete user
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    // Converts User entity to UserResponseDto
    public UserResponseDto convertToResponseDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());
        dto.setRoleId(user.getRole() != null ? user.getRole().getId() : null); // Handle potential null role
        return dto;
    }
}