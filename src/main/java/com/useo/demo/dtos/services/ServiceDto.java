package com.useo.demo.dtos.services;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

public class ServiceDto {

    @NotEmpty(message = "The name is required.")
    @Size(min = 2, max = 100, message = "The length of name must be between 2 and 100 characters.")
    private String name;

    @NotEmpty(message = "The description is required.")
    @Size(min = 2, max = 500, message = "The length of description must be between 2 and 500 characters.")
    private String description;

    @NotNull(message = "The category ID is required.")
    private Long categoryId;

    private List<ServiceDetailsDto> serviceDetails;

    // Getters and Setters

    public String getName() {
        return name;
    }

    public ServiceDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ServiceDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public ServiceDto setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public List<ServiceDetailsDto> getServiceDetails() {
        return serviceDetails;
    }

    public ServiceDto setServiceDetails(List<ServiceDetailsDto> serviceDetails) {
        this.serviceDetails = serviceDetails;
        return this;
    }
}
