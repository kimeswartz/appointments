package com.useo.demo.services;

import com.useo.demo.dtos.AppointmentDto;
import com.useo.demo.entities.Appointment;
import com.useo.demo.entities.Location;
import com.useo.demo.entities.services.ServiceDetails;
import com.useo.demo.entities.Staff;
import com.useo.demo.entities.User;
import com.useo.demo.repositories.AppointmentRepository;
import com.useo.demo.repositories.LocationRepository;
import com.useo.demo.repositories.services.ServiceDetailsRepository;
import com.useo.demo.repositories.StaffRepository;
import com.useo.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final StaffRepository staffRepository;
    private final LocationRepository locationRepository;
    private final ServiceDetailsRepository serviceDetailsRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, UserRepository userRepository, StaffRepository staffRepository, LocationRepository locationRepository, ServiceDetailsRepository serviceDetailsRepository) {
        this.appointmentRepository = appointmentRepository;
        this.userRepository = userRepository;
        this.staffRepository = staffRepository;
        this.locationRepository = locationRepository;
        this.serviceDetailsRepository = serviceDetailsRepository;
    }

    // Create or update an appointment
    public AppointmentDto saveAppointment(AppointmentDto appointmentDto) {
        Appointment appointment = dtoToEntity(appointmentDto);
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return entityToDto(savedAppointment);
    }

    // Find an appointment by ID
    public Optional<AppointmentDto> findAppointmentById(Long id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        return appointment.map(this::entityToDto);
    }

    // Find appointments by user ID
    public List<AppointmentDto> findAppointmentsByUserId(Long userId) {
        List<Appointment> appointments = appointmentRepository.findByUserId(userId);
        return appointments.stream().map(this::entityToDto).toList();
    }

    // Find appointments by staff ID
    public List<AppointmentDto> findAppointmentsByStaffId(Long staffId) {
        List<Appointment> appointments = appointmentRepository.findByStaffId(staffId);
        return appointments.stream().map(this::entityToDto).toList();
    }

    // Find appointments by location ID
    public List<AppointmentDto> findAppointmentsByLocationId(Long locationId) {
        List<Appointment> appointments = appointmentRepository.findByLocationId(locationId);
        return appointments.stream().map(this::entityToDto).toList();
    }

    // Find appointments by service ID
    public List<AppointmentDto> findAppointmentsByServiceId(Long serviceId) {
        List<Appointment> appointments = appointmentRepository.findByServiceId(serviceId);
        return appointments.stream().map(this::entityToDto).toList();
    }

    // Find appointments by confirmation status
    public List<AppointmentDto> findAppointmentsByIsConfirmed(Boolean isConfirmed) {
        List<Appointment> appointments = appointmentRepository.findByIsConfirmed(isConfirmed);
        return appointments.stream().map(this::entityToDto).toList();
    }

    // Convert DTO to entity
    private Appointment dtoToEntity(AppointmentDto dto) {
        Appointment appointment = new Appointment();

        // Fetch related entities
        User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Staff staff = staffRepository.findById(dto.getStaffId()).orElseThrow(() -> new RuntimeException("Staff not found"));
        Location location = locationRepository.findById(dto.getLocationId()).orElseThrow(() -> new RuntimeException("Location not found"));
        ServiceDetails service = serviceDetailsRepository.findById(dto.getServiceId()).orElseThrow(() -> new RuntimeException("Service not found"));

        appointment.setUser(user);
        appointment.setStaff(staff);
        appointment.setLocation(location);
        appointment.setService(service);
        appointment.setAppointmentDateTime(dto.getAppointmentDateTime());
        appointment.setConfirmed(dto.getIsConfirmed());

        // Note: No need to set createdAt and updatedAt since they are managed by Hibernate
        return appointment;
    }

    // Convert entity to DTO
    private AppointmentDto entityToDto(Appointment appointment) {
        AppointmentDto dto = new AppointmentDto();

        dto.setUserId(appointment.getUser().getId());
        dto.setStaffId(appointment.getStaff().getId());
        dto.setLocationId(appointment.getLocation().getId());
        dto.setServiceId(appointment.getService().getId());
        dto.setAppointmentDateTime(appointment.getAppointmentDateTime());
        dto.setIsConfirmed(appointment.isConfirmed());
        dto.setCreatedAt(appointment.getCreatedAt());
        dto.setUpdatedAt(appointment.getUpdatedAt());

        return dto;
    }
}
