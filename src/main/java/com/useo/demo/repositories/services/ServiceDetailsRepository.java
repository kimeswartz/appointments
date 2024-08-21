package com.useo.demo.repositories.services;

import com.useo.demo.entities.services.ServiceDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceDetailsRepository extends CrudRepository<ServiceDetails, Long>{
    Optional<ServiceDetails> findByName(String name);
}