package com.useo.demo.repositories.services;

import com.useo.demo.entities.services.ServiceName;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceRepository extends CrudRepository<ServiceName, Long>{
    Optional<ServiceName> findByName(String name);
}
