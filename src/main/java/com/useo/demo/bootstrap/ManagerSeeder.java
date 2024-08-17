package com.useo.demo.bootstrap;

// Create an Manager role upon startup of the application if not exist in db

import com.useo.demo.dtos.RegisterUserDto;
import com.useo.demo.entities.Role;
import com.useo.demo.entities.RoleEnum;
import com.useo.demo.entities.SaveUser;

import com.useo.demo.repositories.UserRepository;
import com.useo.demo.repositories.RoleRepository;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ManagerSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public ManagerSeeder(
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
        RegisterUserDto userDto = new RegisterUserDto();
        userDto
                .setFullName("Company Manager")
                .setEmail("manager@email.com")
                .setPassword("123456");

        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.MANAGER);
        Optional<SaveUser> optionalUser = userRepository.findByEmail(userDto.getEmail());

        if (optionalRole.isEmpty() || optionalUser.isPresent()) {
            return;
        }

        var user = new SaveUser();
        user.setFullName(userDto.getFullName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(optionalRole.get());


        userRepository.save(user);
    }
}
