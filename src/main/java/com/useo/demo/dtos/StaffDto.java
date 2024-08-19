package com.useo.demo.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public class StaffDto {

    @NotEmpty(message = "The first name is required.")
    @Size(min = 2, max = 100, message = "The length of first name must be between 2 and 100 characters.")
    private String firstName;

    @NotEmpty(message = "The surname is required.")
    @Size(min = 2, max = 100, message = "The length of surname must be between 2 and 100 characters.")
    private String surname;

    @NotEmpty(message = "The email address is required.")
    @Email(message = "The email address is invalid.")
    private String email;

    @NotNull(message = "The availability start time is required.")
    private LocalTime availabilityStart;

    @NotNull(message = "The availability end time is required.")
    private LocalTime availabilityEnd;

    // Getters and Setters

    public String getFirstName() {
        return firstName;
    }

    public StaffDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public StaffDto setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public StaffDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public LocalTime getAvailabilityStart() {
        return availabilityStart;
    }

    public StaffDto setAvailabilityStart(LocalTime availabilityStart) {
        this.availabilityStart = availabilityStart;
        return this;
    }

    public LocalTime getAvailabilityEnd() {
        return availabilityEnd;
    }

    public StaffDto setAvailabilityEnd(LocalTime availabilityEnd) {
        this.availabilityEnd = availabilityEnd;
        return this;
    }
}