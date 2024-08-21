package com.useo.demo.entities.services;

import jakarta.persistence.*;

@Entity
@Table(name = "service_details")
public class ServiceDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false) // Ensures ID is not updated
    private Long id;

    // A name to put in service layer
    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 500)
    private String description;

    @Column(nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private ServiceName service;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public ServiceDetails setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ServiceDetails setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ServiceDetails setDescription(String description) {
        this.description = description;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public ServiceDetails setPrice(Double price) {
        this.price = price;
        return this;
    }

    public ServiceName getService() {
        return service;
    }

    public ServiceDetails setService(ServiceName service) {
        this.service = service;
        return this;
    }
}