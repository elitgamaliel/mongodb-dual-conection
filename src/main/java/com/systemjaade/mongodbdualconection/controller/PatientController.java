package com.systemjaade.mongodbdualconection.controller;

import com.systemjaade.mongodbdualconection.model.Patient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import javax.validation.Valid;


@RestController
@RequestMapping("/patient")
public class PatientController {

    private final ReactiveMongoTemplate mongoTemplate;

    public PatientController(@Qualifier("mongoTemplatePatient") ReactiveMongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @GetMapping("/")
    public Flux<Patient> getAllPatients() {
        return mongoTemplate.findAll(Patient.class);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Patient>> getPatientById(@PathVariable(value = "id") String patientId) {
        return mongoTemplate.findById(patientId, Patient.class)
                .map(patient -> ResponseEntity.ok(patient))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public Mono<Patient> createPatient(@Valid @RequestBody Patient patient) {
        return mongoTemplate.save(patient);
    }
}
