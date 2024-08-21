package com.useo.demo.repositories;

import com.useo.demo.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    // Find appointments by user ID
    List<Appointment> findByUserId(Long userId);

    // Find appointments by staff ID
    List<Appointment> findByStaffId(Long staffId);

    // Find appointments by location ID
    List<Appointment> findByLocationId(Long locationId);

    // Find appointments by service ID
    List<Appointment> findByServiceId(Long serviceId);

    // Find appointments by confirmation status
    List<Appointment> findByIsConfirmed(Boolean isConfirmed);

    // Find appointments by appointment date and time
    List<Appointment> findByAppointmentDateTime(LocalDateTime appointmentDateTime);

    // Optional: Find appointments by a range of appointment date and time
    List<Appointment> findByAppointmentDateTimeBetween(LocalDateTime start, LocalDateTime end);

    // Optional: Find an appointment by its ID (standard method already provided by JpaRepository)
    Optional<Appointment> findById(Long id);
}