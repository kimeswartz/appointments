package com.useo.demo.dtos;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class AppointmentDto {

    @NotNull(message = "The user ID is required.")
    private Long userId;

    @NotNull(message = "The staff ID is required.")
    private Long staffId;

    @NotNull(message = "The location ID is required.")
    private Long locationId;

    @NotNull(message = "The service ID is required.")
    private Long serviceId;

    @NotNull(message = "The appointment date and time is required.")
    private LocalDateTime appointmentDateTime;

    @NotNull(message = "The confirmation status is required.")
    private Boolean isConfirmed;

    @NotNull(message = "The created at timestamp is required.")
    private LocalDateTime createdAt;

    @NotNull(message = "The updated at timestamp is required.")
    private LocalDateTime updatedAt;

    // Getters and Setters

    public Long getUserId() {
        return userId;
    }

    public AppointmentDto setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Long getStaffId() {
        return staffId;
    }

    public AppointmentDto setStaffId(Long staffId) {
        this.staffId = staffId;
        return this;
    }

    public Long getLocationId() {
        return locationId;
    }

    public AppointmentDto setLocationId(Long locationId) {
        this.locationId = locationId;
        return this;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public AppointmentDto setServiceId(Long serviceId) {
        this.serviceId = serviceId;
        return this;
    }

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public AppointmentDto setAppointmentDateTime(LocalDateTime appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
        return this;
    }

    public Boolean getIsConfirmed() {
        return isConfirmed;
    }

    public AppointmentDto setIsConfirmed(Boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public AppointmentDto setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public AppointmentDto setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    @Override
    public String toString() {
        return "AppointmentDto{" +
                "userId=" + userId +
                ", staffId=" + staffId +
                ", locationId=" + locationId +
                ", serviceId=" + serviceId +
                ", appointmentDateTime=" + appointmentDateTime +
                ", isConfirmed=" + isConfirmed +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}