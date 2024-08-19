package com.useo.demo.services;

import com.useo.demo.dtos.TreatmentDto;
import com.useo.demo.entities.Treatment;
import com.useo.demo.repositories.TreatmentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TreatmentService {

    private final TreatmentRepository treatmentRepository;

    public TreatmentService(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }

    public List<Treatment> findAll() {
        List<Treatment> treatments = new ArrayList<>();
        treatmentRepository.findAll().forEach(treatments::add);
        return treatments;
    }

    public Treatment createTreatment(TreatmentDto input) {
        var treatment = new Treatment();
        treatment.setName(input.getName());
        treatment.setDescription(input.getDescription());
        treatment.setDuration(input.getDuration());
        treatment.setPrice(input.getPrice());

        return treatmentRepository.save(treatment);
    }
}
