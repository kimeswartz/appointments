package com.useo.demo.dtos.services;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ServiceDetailsDto {

    @NotEmpty(message = "The name field is required.")
    @Size(min = 2, max = 100, message = "The length of name must be between 2 and 100 characters.")
    private String name;

    @NotEmpty(message = "The description is required.")
    @Size(min = 2, max = 500, message = "The length of description must be between 2 and 500 characters.")
    private String description;

    @NotNull(message = "The price is required.")
    @Positive(message = "The price must be a positive number.")
    private Double price;

    @NotNull(message = "The service ID is required.")
    private Long serviceId;

    public String getName() {
        return name;
    }

    public ServiceDetailsDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ServiceDetailsDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public ServiceDetailsDto setPrice(Double price) {
        this.price = price;
        return this;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public ServiceDetailsDto setServiceId(Long serviceId) {
        this.serviceId = serviceId;
        return this;
    }
}