package com.systemjaade.mongodbdualconection.controller;

import com.systemjaade.mongodbdualconection.model.Doctor;
import com.systemjaade.mongodbdualconection.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ReactiveMongoTemplate doctor;

    @GetMapping("/{id}")
    public Mono<Doctor> getDoctorById(@PathVariable String id) {
        return doctorRepository.findById(id);
    }

    @PostMapping("/")
    public Mono<Doctor> createDoctor(@RequestBody Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // Métodos adicionales para interactuar con la base de datos
}
