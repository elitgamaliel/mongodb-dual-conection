package com.systemjaade.mongodbdualconection.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.systemjaade.mongodbdualconection.repository", reactiveMongoTemplateRef = "mongoTemplateDoctor")
public class MongoConfigDoctor {

    @Bean
    public MongoClient mongoClientDoctor() {
        return MongoClients.create("mongodb://localhost:27017/doctor");
    }

    @Bean
    public ReactiveMongoTemplate mongoTemplate2() {
        return new ReactiveMongoTemplate(mongoClientDoctor(), "doctor");
    }
}