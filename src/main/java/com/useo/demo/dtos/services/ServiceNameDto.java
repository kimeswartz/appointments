package com.useo.demo.dtos.services;

import java.util.List;

public class ServiceNameDto {
    private Long id;
    private String name;
    private String description;
    private Long categoryId;
    private List<ServiceDetailsDto> serviceDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public List<ServiceDetailsDto> getServiceDetails() {
        return serviceDetails;
    }

    public void setServiceDetails(List<ServiceDetailsDto> serviceDetails) {
        this.serviceDetails = serviceDetails;
    }
}
