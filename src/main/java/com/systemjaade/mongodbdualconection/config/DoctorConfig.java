package com.systemjaade.mongodbdualconection.config;

import static java.util.Collections.singletonList;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.systemjaade.mongodbdualconection.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@Configuration
@EnableMongoRepositories(basePackageClasses = DoctorRepository.class, mongoTemplateRef = "doctorMongoTemplate")
@EnableConfigurationProperties
public class DoctorConfig {

    @Bean(name = "doctorProperties")
    @ConfigurationProperties(prefix = "mongodb.doctor")
    @Primary
    public MongoProperties doctorProperties() {
        return new MongoProperties();
    }

    @Bean(name = "doctorMongoClient")
    public MongoClient mongoClient(@Qualifier("doctorProperties") MongoProperties mongoProperties) {

        MongoCredential credential = MongoCredential.createCredential(mongoProperties.getUsername(), mongoProperties.getAuthenticationDatabase(), mongoProperties.getPassword());

        return MongoClients.create(MongoClientSettings.builder()
                .applyToClusterSettings(builder -> builder.hosts(singletonList(new ServerAddress(mongoProperties.getHost(), mongoProperties.getPort()))))
                .credential(credential)
                .build());
    }

    @Primary
    @Bean(name = "doctorMongoDBFactory")
    public ReactiveMongoDatabaseFactory mongoDatabaseFactory(@Qualifier("doctorMongoClient") MongoClient mongoClient, @Qualifier("doctorProperties") MongoProperties mongoProperties) {
        return new SimpleReactiveMongoDatabaseFactory(mongoClient, mongoProperties.getDatabase());
    }

    @Primary
    @Bean(name = "doctorMongoTemplate")
    public ReactiveMongoTemplate mongoTemplate(@Qualifier("doctorMongoDBFactory") ReactiveMongoDatabaseFactory mongoDatabaseFactory) {
        return new ReactiveMongoTemplate(mongoDatabaseFactory);
    }
}
