package com.useo.demo.repositories.role;

import com.useo.demo.entities.role.Role;
import com.useo.demo.entities.role.RoleEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByName(RoleEnum name);
}
