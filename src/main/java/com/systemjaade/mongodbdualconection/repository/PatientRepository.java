package com.systemjaade.mongodbdualconection.repository;

import com.systemjaade.mongodbdualconection.model.Patient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("patient")
public interface PatientRepository extends ReactiveMongoRepository<Patient, String> {
}
