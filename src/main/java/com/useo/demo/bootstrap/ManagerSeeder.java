package com.useo.demo.bootstrap;

// Create an Manager role upon startup of the application if not exist in db

import com.useo.demo.dtos.UserDto;
import com.useo.demo.entities.Role;
import com.useo.demo.entities.RoleEnum;
import com.useo.demo.entities.User;

import com.useo.demo.repositories.UserRepository;
import com.useo.demo.repositories.RoleRepository;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
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
        UserDto userDto = new UserDto();
        userDto
                .setName("Kim")
                .setSurname("Swartz")
                .setEmail("manager@email.com")
                .setPhoneNumber("0763188658")
                .setPassword("123456");

        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.MANAGER);
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());

        if (optionalRole.isEmpty() || optionalUser.isPresent()) {
            return;
        }

        var user = new User();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(optionalRole.get());

        userRepository.save(user);
    }
}
