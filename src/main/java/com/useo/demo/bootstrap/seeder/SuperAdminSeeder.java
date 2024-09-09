package com.useo.demo.bootstrap.seeder;

// Create a SUPER_ADMIN role upon startup of the application if not exist in db

import com.useo.demo.dtos.user.UserSaveRequestDto;
import com.useo.demo.entities.role.Role;
import com.useo.demo.entities.role.RoleEnum;
import com.useo.demo.entities.user.User;

import com.useo.demo.repositories.user.UserRepository;
import com.useo.demo.repositories.role.RoleRepository;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SuperAdminSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public SuperAdminSeeder(
            @NonNull UserRepository userRepository,
            @NonNull RoleRepository roleRepository,
            @NonNull PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(@NonNull ContextRefreshedEvent contextRefreshedEvent) {
        this.createManager();
    }

    private void createManager() {
        UserSaveRequestDto userSaveRequestDto = new UserSaveRequestDto();
        userSaveRequestDto
                .setName("Super")
                .setSurname("Admin")
                .setEmail("super@email.com")
                .setPhoneNumber("0700123456")
                .setPassword("123456");

        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.SUPER_ADMIN);
        Optional<User> optionalUser = userRepository.findByEmail(userSaveRequestDto.getEmail());

        if (optionalRole.isEmpty() || optionalUser.isPresent()) {
            return;
        }

        var user = new User();
        user.setName(userSaveRequestDto.getName());
        user.setSurname(userSaveRequestDto.getSurname());
        user.setEmail(userSaveRequestDto.getEmail());
        user.setPhoneNumber(userSaveRequestDto.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(userSaveRequestDto.getPassword()));
        user.setRole(optionalRole.get());

        userRepository.save(user);
    }
}