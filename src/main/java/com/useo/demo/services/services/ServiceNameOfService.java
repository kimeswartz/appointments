package com.useo.demo.services.services;

import com.useo.demo.dtos.services.ServiceDto;
import com.useo.demo.dtos.services.ServiceNameDto;
import com.useo.demo.dtos.services.ServiceDetailsDto;
import com.useo.demo.entities.services.ServiceName;
import com.useo.demo.entities.services.Category;
import com.useo.demo.entities.services.ServiceDetails;
import com.useo.demo.repositories.services.ServiceRepository;
import com.useo.demo.repositories.services.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ServiceNameOfService {

    private final ServiceRepository serviceRepository;
    private final CategoryRepository categoryRepository;

    public ServiceNameOfService(ServiceRepository serviceRepository, CategoryRepository categoryRepository) {
        this.serviceRepository = serviceRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<ServiceNameDto> findAll() {
        Iterable<ServiceName> serviceNames = serviceRepository.findAll();
        return StreamSupport.stream(serviceNames.spliterator(), false)
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public ServiceNameDto findById(Long id) {
        ServiceName serviceName = findServiceById(id);
        return convertToDto(serviceName);
    }

    @Transactional
    public ServiceNameDto createService(ServiceDto input) {
        Category category = findCategoryById(input.getCategoryId());
        ServiceName serviceName = new ServiceName();
        return convertToDto(saveServiceName(input, category, serviceName));
    }

    @Transactional
    public ServiceNameDto updateService(Long id, ServiceDto input) {
        ServiceName existingService = findServiceById(id);
        Category category = findCategoryById(input.getCategoryId());
        return convertToDto(saveServiceName(input, category, existingService));
    }

    @Transactional
    public void deleteService(Long id) {
        ServiceName serviceName = findServiceById(id);
        serviceRepository.delete(serviceName);
    }

    public boolean existsById(Long id) {
        return serviceRepository.existsById(id);
    }

    private ServiceName saveServiceName(ServiceDto input, Category category, ServiceName serviceName) {
        serviceName.setName(input.getName());
        serviceName.setDescription(input.getDescription());
        serviceName.setCategory(category);
        serviceName.setServiceDetails(mapServiceDetails(input.getServiceDetails(), serviceName));
        return serviceRepository.save(serviceName);
    }

    private List<ServiceDetails> mapServiceDetails(List<ServiceDetailsDto> detailsDtos, ServiceName serviceName) {
        if (detailsDtos == null) {
            return List.of();
        }
        return detailsDtos.stream()
                .map(dto -> {
                    ServiceDetails serviceDetails = new ServiceDetails();
                    serviceDetails.setName(dto.getName());
                    serviceDetails.setDescription(dto.getDescription());
                    serviceDetails.setPrice(dto.getPrice());
                    serviceDetails.setService(serviceName);
                    return serviceDetails;
                })
                .collect(Collectors.toList());
    }

    private ServiceNameDto convertToDto(ServiceName serviceName) {
        ServiceNameDto dto = new ServiceNameDto();
        dto.setId(serviceName.getId());
        dto.setName(serviceName.getName());
        dto.setDescription(serviceName.getDescription());
        dto.setCategoryId(serviceName.getCategory().getId());

        List<ServiceDetailsDto> detailsDtos = serviceName.getServiceDetails().stream()
                .map(detail -> {
                    ServiceDetailsDto detailsDto = new ServiceDetailsDto();
                    detailsDto.setName(detail.getName());
                    detailsDto.setDescription(detail.getDescription());
                    detailsDto.setPrice(detail.getPrice());
                    detailsDto.setServiceId(detail.getService().getId());  // Assuming ServiceDetails has a reference to ServiceName
                    return detailsDto;
                })
                .collect(Collectors.toList());

        dto.setServiceDetails(detailsDtos);
        return dto;
    }

    private ServiceName findServiceById(Long id) {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Service not found with ID: " + id));
    }

    private Category findCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found with ID: " + id));
    }
}