package com.systemjaade.mongodbdualconection.controller;

import com.systemjaade.mongodbdualconection.model.Doctor;
import com.systemjaade.mongodbdualconection.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class DoctorController {

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorController(@Qualifier("doctor") DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @GetMapping("/doctors")
    public Flux<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @PostMapping("/doctors")
    public Mono<Doctor> addDoctor(@RequestBody Doctor doctor) {
        return doctorRepository.save(doctor);
    }

}