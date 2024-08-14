package com.useo.demo.repositories;

import com.useo.demo.entities.SaveUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<SaveUser, Integer> {
    Optional<SaveUser> findByEmail(String email);
}
