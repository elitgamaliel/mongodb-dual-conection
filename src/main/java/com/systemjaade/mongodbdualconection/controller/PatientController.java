package com.systemjaade.mongodbdualconection.controller;

import com.systemjaade.mongodbdualconection.model.Patient;
import com.systemjaade.mongodbdualconection.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import javax.validation.Valid;


@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/{id}")
    public Mono<Patient> getPatientById(@PathVariable String id) {
        return patientRepository.findById(id);
    }

    @PostMapping("/")
    public Mono<Patient> createPatient(@RequestBody Patient patient) {
        return patientRepository.save(patient);
    }

    // Métodos adicionales para interactuar con la base de datos
}