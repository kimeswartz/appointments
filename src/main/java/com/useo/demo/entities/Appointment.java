package com.useo.demo.entities;

import com.useo.demo.entities.services.ServiceDetails;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Table(name = "appointment")
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staff;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private ServiceDetails service;

    @Column(name = "appointment_datetime", nullable = false)
    private LocalDateTime appointmentDateTime;

    @Column(nullable = false)
    private boolean isConfirmed;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Constructors
    public Appointment() {}

    public Appointment(User user, Staff staff, Location location, ServiceDetails service, LocalDateTime appointmentDateTime, boolean isConfirmed) {
        this.user = user;
        this.staff = staff;
        this.location = location;
        this.service = service;
        this.appointmentDateTime = appointmentDateTime;
        this.isConfirmed = isConfirmed;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public Appointment setId(Long id) {
        this.id = id;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Appointment setUser(User user) {
        this.user = user;
        return this;
    }

    public Staff getStaff() {
        return staff;
    }

    public Appointment setStaff(Staff staff) {
        this.staff = staff;
        return this;
    }

    public Location getLocation() {
        return location;
    }

    public Appointment setLocation(Location location) {
        this.location = location;
        return this;
    }

    public ServiceDetails getService() {
        return service;
    }

    public Appointment setService(ServiceDetails service) {
        this.service = service;
        return this;
    }

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public Appointment setAppointmentDateTime(LocalDateTime appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
        return this;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public Appointment setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", user=" + user.getName() + " " + user.getSurname() +
                ", staff=" + staff.getFirstName() + " " + staff.getSurname() +
                ", location=" + location.getName() +
                ", service=" + service.getName() +
                ", appointmentDateTime=" + appointmentDateTime +
                ", isConfirmed=" + isConfirmed +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}