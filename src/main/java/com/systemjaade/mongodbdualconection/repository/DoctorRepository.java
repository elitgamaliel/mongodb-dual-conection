package com.systemjaade.mongodbdualconection.repository;

import com.systemjaade.mongodbdualconection.model.Doctor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("doctor")
public interface DoctorRepository extends ReactiveMongoRepository<Doctor, String> {}