package com.useo.demo.entities.services;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ServiceName> serviceNames;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category setName(String name) {
        this.name = name;
        return this;
    }

    public List<ServiceName> getServices() {
        return serviceNames;
    }

    public Category setServices(List<ServiceName> serviceNames) {
        this.serviceNames = serviceNames;
        return this;
    }
}
