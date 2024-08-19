package com.useo.demo.services;

import com.useo.demo.dtos.LocationDto;
import com.useo.demo.entities.Location;
import com.useo.demo.repositories.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> findAll() {
        List<Location> location = new ArrayList<>();
        locationRepository.findAll().forEach(location::add);
        return location;
    }

    public Location createLocation(LocationDto input) {
        var location = new Location();
        location.setName(input.getName());
        location.setLocation(input.getLocation());

        return locationRepository.save(location);
    }
}
