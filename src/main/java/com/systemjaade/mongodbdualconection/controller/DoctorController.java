package com.systemjaade.mongodbdualconection.controller;

import com.systemjaade.mongodbdualconection.model.Doctor;
import com.systemjaade.mongodbdualconection.service.DoctorService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/patients")
    public Flux<Doctor> findDoctorsByCondition(@RequestParam String condition) {
        return doctorService.findDoctorsByCondition(condition);
    }
}