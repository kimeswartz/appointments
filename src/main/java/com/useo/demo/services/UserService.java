package com.useo.demo.services;

/*
  Service layer that provides logic for managing User entities
  The layer acts as an intermediary between the controller and the UserRepository
 */

import com.useo.demo.entities.SaveUser;
import com.useo.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<SaveUser> findAll() {
        List<SaveUser> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);

        return users;
    }

}