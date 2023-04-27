package com.systemjaade.mongodbdualconection.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.systemjaade.mongodbdualconection.repository", reactiveMongoTemplateRef = "mongoTemplatePatient")
public class MongoConfigPatient {

    @Bean
    public MongoClient mongoClientPatient() {
        return MongoClients.create("mongodb://localhost:27017/patient");
    }

    @Bean
    public ReactiveMongoTemplate mongoTemplate1() {
        return new ReactiveMongoTemplate(mongoClientPatient(), "patient");
    }
}