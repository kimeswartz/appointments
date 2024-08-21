package com.useo.demo.dtos.services;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.List;

public class ServiceCategoryDto {

    @NotEmpty(message = "The name is required.")
    @Size(min = 2, max = 100, message = "The length of name must be between 2 and 100 characters.")
    private String name;

    private List<Long> serviceIds;

    public String getName() {
        return name;
    }

    public ServiceCategoryDto setName(String name) {
        this.name = name;
        return this;
    }

    public List<Long> getServiceIds() {
        return serviceIds;
    }

    public ServiceCategoryDto setServiceIds(List<Long> serviceIds) {
        this.serviceIds = serviceIds;
        return this;
    }
}

