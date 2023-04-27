package com.systemjaade.mongodbdualconection.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

@Configuration
public class MongoConfig {

    @Value("${spring.data.mongodb.patient.uri}")
    private String patientUri;

    @Value("${spring.data.mongodb.patient.database}")
    private String patientDatabase;

    @Value("${spring.data.mongodb.doctor.uri}")
    private String doctorUri;

    @Value("${spring.data.mongodb.doctor.database}")
    private String doctorDatabase;

    @Bean
    public MongoClient patientMongoClient() {
        return MongoClients.create(patientUri);
    }

    @Bean
    public MongoClient doctorMongoClient() {
        return MongoClients.create(doctorUri);
    }

    @Bean
    public ReactiveMongoTemplate patientReactiveMongoTemplate() {
        return new ReactiveMongoTemplate(patientMongoClient(), patientDatabase);
    }

    @Bean
    public ReactiveMongoTemplate doctorReactiveMongoTemplate() {
        return new ReactiveMongoTemplate(doctorMongoClient(), doctorDatabase);
    }
}
