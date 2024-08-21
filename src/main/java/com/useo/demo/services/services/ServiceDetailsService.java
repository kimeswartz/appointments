package com.useo.demo.services.services;

import com.useo.demo.dtos.services.ServiceDetailsDto;
import com.useo.demo.entities.services.ServiceDetails;
import com.useo.demo.entities.services.ServiceName;
import com.useo.demo.repositories.services.ServiceDetailsRepository;
import com.useo.demo.repositories.services.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceDetailsService {

    private final ServiceDetailsRepository serviceDetailsRepository;
    private final ServiceRepository serviceNameRepository;

    public ServiceDetailsService(ServiceDetailsRepository serviceDetailsRepository, ServiceRepository serviceRepository) {
        this.serviceDetailsRepository = serviceDetailsRepository;
        this.serviceNameRepository = serviceRepository;
    }

    public List<ServiceDetails> findAll() {
        List<ServiceDetails> serviceDetails = new ArrayList<>();
        serviceDetailsRepository.findAll().forEach(serviceDetails::add);
        return serviceDetails;
    }

    public ServiceDetails findById(Long id) {
        return serviceDetailsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Service details not found with ID: " + id));
    }

    public ServiceDetails createServiceDetails(ServiceDetailsDto input) {
        Optional<ServiceName> optionalServiceName = serviceNameRepository.findById(input.getServiceId());
        if (optionalServiceName.isEmpty()) {
            throw new IllegalArgumentException("Service not found with ID: " + input.getServiceId());
        }

        var serviceDetails = new ServiceDetails()
                .setName(input.getName())
                .setDescription(input.getDescription())
                .setPrice(input.getPrice())
                .setService(optionalServiceName.get());

        return serviceDetailsRepository.save(serviceDetails);
    }

    public ServiceDetails updateServiceDetails(Long id, ServiceDetailsDto input) {
        Optional<ServiceDetails> optionalServiceDetails = serviceDetailsRepository.findById(id);
        if (optionalServiceDetails.isEmpty()) {
            throw new IllegalArgumentException("Service details not found with ID: " + id);
        }

        Optional<ServiceName> optionalServiceName = serviceNameRepository.findById(input.getServiceId());
        if (optionalServiceName.isEmpty()) {
            throw new IllegalArgumentException("Service not found with ID: " + input.getServiceId());
        }

        var serviceDetails = optionalServiceDetails.get()
                .setName(input.getName())
                .setDescription(input.getDescription())
                .setPrice(input.getPrice())
                .setService(optionalServiceName.get());

        return serviceDetailsRepository.save(serviceDetails);
    }

    public void deleteServiceDetails(Long id) {
        if (!serviceDetailsRepository.existsById(id)) {
            throw new IllegalArgumentException("Service details not found with ID: " + id);
        }
        serviceDetailsRepository.deleteById(id);
    }
}