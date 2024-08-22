package com.useo.demo.services;

import com.useo.demo.dtos.LocationDto;
import com.useo.demo.entities.Location;
import com.useo.demo.repositories.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> findAll() {
        List<Location> locations = new ArrayList<>();
        locationRepository.findAll().forEach(locations::add);
        return locations;
    }

    public Location createLocation(LocationDto input) {
        var location = new Location();
        location.setName(input.getName());
        location.setLocation(input.getLocation());

        return locationRepository.save(location);
    }

    public Location updateLocation(Long id, LocationDto input) {
        Optional<Location> existingLocation = locationRepository.findById(id);

        if (existingLocation.isPresent()) {
            var location = existingLocation.get();
            location.setName(input.getName());
            location.setLocation(input.getLocation());

            return locationRepository.save(location);
        } else {
            throw new RuntimeException("Location not found with id: " + id);
        }
    }

    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }

    public Optional<Location> findById(Long id) {
        return locationRepository.findById(id);
    }
}
