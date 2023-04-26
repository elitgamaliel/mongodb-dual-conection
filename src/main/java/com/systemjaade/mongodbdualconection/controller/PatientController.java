package com.systemjaade.mongodbdualconection.controller;

import com.systemjaade.mongodbdualconection.model.Patient;
import com.systemjaade.mongodbdualconection.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class PatientController {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientController(@Qualifier("patient") PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping("/patients")
    public Flux<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @PostMapping("/patients")
    public Mono<Patient> addPatient(@RequestBody Patient patient) {
        return patientRepository.save(patient);
    }

}