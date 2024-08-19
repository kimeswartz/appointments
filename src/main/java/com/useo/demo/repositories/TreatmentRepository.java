package com.useo.demo.repositories;

import com.useo.demo.entities.Treatment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TreatmentRepository extends CrudRepository<Treatment, Integer>{
    Optional<Treatment> findByName(String name);
}
