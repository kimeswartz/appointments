package com.useo.demo.services.user;

import com.useo.demo.dtos.user.AdminSaveRequestDto;
import com.useo.demo.entities.role.Role;
import com.useo.demo.entities.role.RoleEnum;
import com.useo.demo.entities.user.Admin;

import com.useo.demo.repositories.user.AdminRepository;
import com.useo.demo.repositories.role.RoleRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminService(AdminRepository adminRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Retrieve all admins
    public List<Admin> findAll() {
        List<Admin> users = new ArrayList<>();
        adminRepository.findAll().forEach(users::add);
        return users;
    }

    // Create a new admin with 'admin' role
    public Admin createAdmin(AdminSaveRequestDto input) {
        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.ADMIN);
        if (optionalRole.isEmpty()) {
            throw new RuntimeException("Role USER not found");
        }

        var user = new Admin()
                .setName(input.getName())
                .setSurname(input.getSurname())
                .setEmail(input.getEmail())
                .setPhoneNumber(input.getPhoneNumber())
                .setPassword(passwordEncoder.encode(input.getPassword()))
                .setRole(optionalRole.get());

        return adminRepository.save(user);
    }

    // Update an existing admin
    public Admin updateAdmin(Long id, AdminSaveRequestDto input) {
        Optional<Admin> existingAdminOpt = adminRepository.findById(id);
        if (existingAdminOpt.isPresent()) {
            Admin existingAdmin = existingAdminOpt.get();
            existingAdmin.setName(input.getName())
                    .setSurname(input.getSurname())
                    .setEmail(input.getEmail())
                    .setPhoneNumber(input.getPhoneNumber());

            // Password update check
            if (input.getPassword() != null && !input.getPassword().isEmpty()) {
                existingAdmin.setPassword(passwordEncoder.encode(input.getPassword()));
            }

            // Role is not updated
            return adminRepository.save(existingAdmin);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    // Retrieve admin by ID
    public Optional<Admin> findById(Long id) {
        return adminRepository.findById(id);
    }

    // Delete admin-user, function removes the role association before deleting an admin.
    @Transactional
    public void deleteAdmin(Long id) {
        Optional<Admin> adminOpt = adminRepository.findById(id);
        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();

            admin.setRole(null); // Remove association
            adminRepository.save(admin); // Save changes
            adminRepository.delete(admin); // Then delete user

        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }
}