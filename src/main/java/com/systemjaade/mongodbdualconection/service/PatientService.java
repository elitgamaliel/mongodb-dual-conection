package com.systemjaade.mongodbdualconection.service;

import com.systemjaade.mongodbdualconection.model.Patient;
import com.systemjaade.mongodbdualconection.repository.patient.PatientRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Flux<Patient> findAll() {
        return patientRepository.findAll();
    }

    public Mono<Patient> add(Patient patient) {
        return patientRepository.save(patient);
    }
}
