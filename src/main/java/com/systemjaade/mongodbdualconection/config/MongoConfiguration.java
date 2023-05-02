package com.systemjaade.mongodbdualconection.config;


import com.mongodb.*;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

@Configuration
public class MongoConfiguration {

    private final CustomMongoProperties customMongoProperties;

    public MongoConfiguration(CustomMongoProperties customMongoProperties) {
        this.customMongoProperties = customMongoProperties;
    }

    @Primary
    @Bean
    public MongoClient reactiveMongoClientDoctor() {
        return MongoClients.create(createMongoClientSettings(customMongoProperties.getDoctor()));
    }

    @Bean
    public MongoClient reactiveMongoClientPatient() {
        return MongoClients.create(createMongoClientSettings(customMongoProperties.getPatient()));
    }

    @Primary
    @Bean("mongoTemplateDoctor")
    public ReactiveMongoTemplate reactiveMongoTemplateDoctor(){
        return new ReactiveMongoTemplate(reactiveMongoClientDoctor(),customMongoProperties.getDoctor().getDatabase());
    }
    @Bean("mongoTemplatePatient")
    public ReactiveMongoTemplate reactiveMongoTemplatePatient(){
        return new ReactiveMongoTemplate(reactiveMongoClientPatient(),customMongoProperties.getPatient().getDatabase());
    }


    private MongoClientSettings createMongoClientSettings(MongoProperties mongoProperties){

        ConnectionString ConnectionString = new ConnectionString(mongoProperties.getUri());

        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .readConcern(ReadConcern.DEFAULT)
                .writeConcern(WriteConcern.MAJORITY)
                .readPreference(ReadPreference.primary())
                .applyConnectionString(ConnectionString)
                .build();
        return mongoClientSettings;
    }
}
