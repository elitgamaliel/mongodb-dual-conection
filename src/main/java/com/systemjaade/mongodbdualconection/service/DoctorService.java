package com.systemjaade.mongodbdualconection.service;

import com.systemjaade.mongodbdualconection.model.Doctor;
import com.systemjaade.mongodbdualconection.repository.DoctorRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Flux<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    public Mono<Doctor> add(Doctor doctor) {
        return doctorRepository.save(doctor);
    }
}
