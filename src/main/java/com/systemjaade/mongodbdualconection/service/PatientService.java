package com.systemjaade.mongodbdualconection.service;

import com.systemjaade.mongodbdualconection.model.Patient;
import com.systemjaade.mongodbdualconection.repository.PatientRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Flux<Patient> findPatientsByCondition(String condition) {
        return patientRepository.findAll();
    }
}
