package com.useo.demo.services;

import com.useo.demo.dtos.StaffDto;
import com.useo.demo.entities.Staff;
import com.useo.demo.repositories.StaffRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StaffService {

    private final StaffRepository staffRepository;

    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public List<Staff> findAll() {
        List<Staff> staffList = new ArrayList<>();
        staffRepository.findAll().forEach(staffList::add);
        return staffList;
    }

    public Staff createStaff(StaffDto input) {
        var staff = new Staff();
        staff.setFirstName(input.getFirstName());
        staff.setSurname(input.getSurname());
        staff.setEmail(input.getEmail());
        staff.setAvailabilityStart(input.getAvailabilityStart());
        staff.setAvailabilityEnd(input.getAvailabilityEnd());

        return staffRepository.save(staff);
    }

    public Staff updateStaff(Long id, StaffDto input) {
        Optional<Staff> existingStaff = staffRepository.findById(id);

        if (existingStaff.isPresent()) {
            var staff = existingStaff.get();
            staff.setFirstName(input.getFirstName());
            staff.setSurname(input.getSurname());
            staff.setEmail(input.getEmail());
            staff.setAvailabilityStart(input.getAvailabilityStart());
            staff.setAvailabilityEnd(input.getAvailabilityEnd());

            return staffRepository.save(staff);
        } else {
            throw new RuntimeException("Staff not found with id: " + id);
        }
    }

    public void deleteStaff(Long id) {
        staffRepository.deleteById(id);
    }

    public Optional<Staff> findById(Long id) {
        return staffRepository.findById(id);
    }
}
