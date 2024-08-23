package com.useo.demo.services.services;

import com.useo.demo.dtos.services.ServiceDetailsDto;
import com.useo.demo.entities.services.ServiceDetails;
import com.useo.demo.entities.services.ServiceName;
import com.useo.demo.repositories.services.ServiceDetailsRepository;
import com.useo.demo.repositories.services.ServiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ServiceOfDetailsService {

    private final ServiceDetailsRepository serviceDetailsRepository;
    private final ServiceRepository serviceRepository;

    public ServiceOfDetailsService(ServiceDetailsRepository serviceDetailsRepository, ServiceRepository serviceRepository) {
        this.serviceDetailsRepository = serviceDetailsRepository;
        this.serviceRepository = serviceRepository;
    }

    public List<ServiceDetailsDto> findAll() {
        Iterable<ServiceDetails> serviceDetails = serviceDetailsRepository.findAll();
        return StreamSupport.stream(serviceDetails.spliterator(), false)
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public ServiceDetailsDto findById(Long id) {
        ServiceDetails serviceDetails = findServiceDetailsById(id);
        return convertToDto(serviceDetails);
    }

    @Transactional
    public ServiceDetailsDto createServiceDetails(ServiceDetailsDto input) {
        ServiceName serviceName = findServiceNameById(input.getServiceId());
        ServiceDetails serviceDetails = new ServiceDetails();
        return convertToDto(saveServiceDetails(input, serviceName, serviceDetails));
    }

    @Transactional
    public ServiceDetailsDto updateServiceDetails(Long id, ServiceDetailsDto input) {
        ServiceDetails existingServiceDetails = findServiceDetailsById(id);
        ServiceName serviceName = findServiceNameById(input.getServiceId());
        return convertToDto(saveServiceDetails(input, serviceName, existingServiceDetails));
    }

    @Transactional
    public void deleteServiceDetails(Long id) {
        ServiceDetails serviceDetails = findServiceDetailsById(id);
        serviceDetailsRepository.delete(serviceDetails);
    }

    private ServiceDetails saveServiceDetails(ServiceDetailsDto input, ServiceName serviceName, ServiceDetails serviceDetails) {
        serviceDetails.setName(input.getName());
        serviceDetails.setDescription(input.getDescription());
        serviceDetails.setPrice(input.getPrice());
        serviceDetails.setService(serviceName);
        return serviceDetailsRepository.save(serviceDetails);
    }

    private ServiceDetailsDto convertToDto(ServiceDetails serviceDetails) {
        ServiceDetailsDto dto = new ServiceDetailsDto();
        dto.setName(serviceDetails.getName());
        dto.setDescription(serviceDetails.getDescription());
        dto.setPrice(serviceDetails.getPrice());
        dto.setServiceId(serviceDetails.getService().getId()); // Assuming ServiceDetails has a reference to ServiceName
        return dto;
    }

    private ServiceDetails findServiceDetailsById(Long id) {
        return serviceDetailsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Service details not found with ID: " + id));
    }

    private ServiceName findServiceNameById(Long id) {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Service not found with ID: " + id));
    }
}