package com.useo.demo.repositories;

import com.useo.demo.entities.Staff;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffRepository extends CrudRepository<Staff, Integer> {
    Optional<Staff> findByEmail(String email);
}
