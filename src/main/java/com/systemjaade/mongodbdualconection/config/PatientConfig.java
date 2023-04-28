package com.systemjaade.mongodbdualconection.config;

import static java.util.Collections.singletonList;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.systemjaade.mongodbdualconection.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@Configuration
@EnableMongoRepositories(basePackageClasses = PatientRepository.class, mongoTemplateRef = "patientMongoTemplate")
@EnableConfigurationProperties
public class PatientConfig {

    @Bean(name = "patientProperties")
    @ConfigurationProperties(prefix = "mongodb.patient")
    public MongoProperties patientProperties() {
        return new MongoProperties();
    }

    @Bean(name = "patientMongoClient")
    public MongoClient mongoClient(@Qualifier("patientProperties") MongoProperties mongoProperties) {

        MongoCredential credential = MongoCredential.createCredential(mongoProperties.getUsername(), mongoProperties.getAuthenticationDatabase(), mongoProperties.getPassword());

        return MongoClients.create(MongoClientSettings.builder()
                .applyToClusterSettings(builder -> builder.hosts(singletonList(new ServerAddress(mongoProperties.getHost(), mongoProperties.getPort()))))
                .credential(credential)
                .build());
    }

    @Bean(name = "patientMongoDBFactory")
    public ReactiveMongoDatabaseFactory mongoDatabaseFactory(@Qualifier("patientMongoClient") MongoClient mongoClient, @Qualifier("patientProperties") MongoProperties mongoProperties) {
        return new SimpleReactiveMongoDatabaseFactory(mongoClient, mongoProperties.getDatabase());
    }

    @Bean(name = "patientMongoTemplate")
    public ReactiveMongoTemplate mongoTemplate(@Qualifier("patientMongoDBFactory") ReactiveMongoDatabaseFactory mongoDatabaseFactory) {
        return new ReactiveMongoTemplate(mongoDatabaseFactory);
    }
}
