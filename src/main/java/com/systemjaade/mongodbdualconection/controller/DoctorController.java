package com.systemjaade.mongodbdualconection.controller;

import com.systemjaade.mongodbdualconection.model.Doctor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private final ReactiveMongoTemplate mongoTemplate;

    public DoctorController(@Qualifier("mongoTemplate") ReactiveMongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @GetMapping("/")
    public Flux<Doctor> getAllDoctors() {
        return mongoTemplate.findAll(Doctor.class);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Doctor>> getDoctorById(@PathVariable(value = "id") String doctorId) {
        return mongoTemplate.findById(doctorId, Doctor.class)
                .map(doctor -> ResponseEntity.ok(doctor))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public Mono<Doctor> createDoctor(@Valid @RequestBody Doctor doctor) {
        return mongoTemplate.save(doctor);
    }
}