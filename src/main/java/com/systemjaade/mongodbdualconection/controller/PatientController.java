package com.systemjaade.mongodbdualconection.controller;

import com.systemjaade.mongodbdualconection.model.Patient;
import com.systemjaade.mongodbdualconection.service.PatientService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/patients")
    public Flux<Patient> findAll() {
        return patientService.findAll();
    }

    @PostMapping("/patient")
    public Mono<Patient> add(@RequestBody Patient patient) {
        return patientService.add(patient);
    }
}