package com.useo.demo.controllers;

import com.useo.demo.dtos.TreatmentDto;
import com.useo.demo.entities.Treatment;
import com.useo.demo.services.TreatmentService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/edit")
@RestController
public class TreatmentController {
    private final TreatmentService treatmentService;

    public TreatmentController(TreatmentService treatmentService) {
        this.treatmentService = treatmentService;
    }

    @PostMapping
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Treatment> createTreatment(@RequestBody TreatmentDto registerUserDto) {
        Treatment createTreatment = treatmentService.createTreatment(registerUserDto);

        return ResponseEntity.ok(createTreatment);
    }

}



