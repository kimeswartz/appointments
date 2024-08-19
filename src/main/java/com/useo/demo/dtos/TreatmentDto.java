package com.useo.demo.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class TreatmentDto {

    @NotEmpty(message = "The name is required.")
    @Size(min = 2, max = 100, message = "The length of name must be between 2 and 100 characters.")
    private String name;

    @Size(max = 255, message = "The length of description must not exceed 255 characters.")
    private String description;

    @NotNull(message = "The duration is required.")
    @Positive(message = "The duration must be a positive number.")
    private Integer duration;

    @NotNull(message = "The price is required.")
    @Positive(message = "The price must be a positive number.")
    private Double price;

    // Getters and Setters

    public String getName() {
        return name;
    }

    public TreatmentDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TreatmentDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public TreatmentDto setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public TreatmentDto setPrice(Double price) {
        this.price = price;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }


    public Double getPrice() {
        return price;
    }

}
