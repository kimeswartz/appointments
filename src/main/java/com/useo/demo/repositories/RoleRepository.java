package com.useo.demo.repositories;

import com.useo.demo.entities.Role;
import com.useo.demo.entities.RoleEnum;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByName(RoleEnum name);
}
