package com.useo.demo.services.services;

import com.useo.demo.dtos.services.ServiceDto;
import com.useo.demo.entities.services.ServiceName;
import com.useo.demo.entities.services.Category;
import com.useo.demo.entities.services.ServiceDetails;
import com.useo.demo.repositories.services.ServiceRepository;
import com.useo.demo.repositories.services.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceOfNameService {

    private final ServiceRepository serviceRepository;
    private final CategoryRepository categoryRepository;

    public ServiceOfNameService(ServiceRepository serviceRepository, CategoryRepository categoryRepository) {
        this.serviceRepository = serviceRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<ServiceName> findAll() {
        List<ServiceName> serviceNames = new ArrayList<>();
        serviceRepository.findAll().forEach(serviceNames::add);
        return serviceNames;
    }

    @Transactional
    public ServiceName createService(ServiceDto input) {
        Category category = categoryRepository.findById(input.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found with ID: " + input.getCategoryId()));

        ServiceName serviceName = new ServiceName();
        return getServiceName(input, category, serviceName);
    }

    private ServiceName getServiceName(ServiceDto input, Category category, ServiceName serviceName) {
        serviceName.setName(input.getName());
        serviceName.setDescription(input.getDescription());
        serviceName.setCategory(category);

        List<ServiceDetails> serviceDetailsList = new ArrayList<>();
        if (input.getServiceDetails() != null) {
            for (var detailsDto : input.getServiceDetails()) {
                ServiceDetails serviceDetails = new ServiceDetails();
                serviceDetails.setName(detailsDto.getName());
                serviceDetails.setDescription(detailsDto.getDescription());
                serviceDetails.setPrice(detailsDto.getPrice());
                serviceDetails.setService(serviceName);
                serviceDetailsList.add(serviceDetails);
            }
        }
        serviceName.setServiceDetails(serviceDetailsList);

        return serviceRepository.save(serviceName);
    }

    @Transactional
    public ServiceName updateService(Long id, ServiceDto input) {
        ServiceName existingService = serviceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Service not found with ID: " + id));

        Category category = categoryRepository.findById(input.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found with ID: " + input.getCategoryId()));

        return getServiceName(input, category, existingService);
    }

    // Find a service by ID
    public ServiceName findById(Long id) {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Service not found with ID: " + id));
    }

    // Check if a service exists by ID
    public boolean existsById(Long id) {
        return serviceRepository.existsById(id);
    }

    // Delete a service by ID
    @Transactional
    public void deleteService(Long id) {
        if (!serviceRepository.existsById(id)) {
            throw new IllegalArgumentException("Service not found with ID: " + id);
        }
        serviceRepository.deleteById(id);
    }
}
