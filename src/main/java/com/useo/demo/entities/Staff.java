package com.useo.demo.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalTime;
import java.time.LocalDateTime;

@Table(name = "staff")
@Entity
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, length = 255)
    private String firstName;

    @Column(nullable = false, length = 255)
    private String surname;

    @Column(unique = true, length = 100, nullable = false)
    private String email;

    @Column(nullable = false, name = "availability_start")
    private LocalTime availabilityStart;

    @Column(nullable = false, name = "availability_end")
    private LocalTime availabilityEnd;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public Staff setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Staff setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public Staff setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Staff setEmail(String email) {
        this.email = email;
        return this;
    }

    public LocalTime getAvailabilityStart() {
        return availabilityStart;
    }

    public Staff setAvailabilityStart(LocalTime availabilityStart) {
        this.availabilityStart = availabilityStart;
        return this;
    }

    public LocalTime getAvailabilityEnd() {
        return availabilityEnd;
    }

    public Staff setAvailabilityEnd(LocalTime availabilityEnd) {
        this.availabilityEnd = availabilityEnd;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

}