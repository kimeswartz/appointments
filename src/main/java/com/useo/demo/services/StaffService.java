package com.useo.demo.services;

import com.useo.demo.dtos.StaffDto;
import com.useo.demo.entities.Staff;
import com.useo.demo.repositories.StaffRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StaffService {

    private final StaffRepository staffRepository;

    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public List<Staff> findAll() {
        List<Staff> staff = new ArrayList<>();
        staffRepository.findAll().forEach(staff::add);
        return staff;
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
}
