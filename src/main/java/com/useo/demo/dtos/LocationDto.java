package com.useo.demo.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class LocationDto {

    @NotEmpty(message = "The name is required.")
    @Size(min = 2, max = 100, message = "The length of name must be between 2 and 100 characters.")
    private String name;

    @NotEmpty(message = "The location is required.")
    @Size(min = 2, max = 255, message = "The length of location must be between 2 and 255 characters.")
    private String location;

    public String getName() {
        return name;
    }

    public LocationDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public LocationDto setLocation(String location) {
        this.location = location;
        return this;
    }
}

