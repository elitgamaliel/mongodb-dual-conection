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
    public MongoClient reactiveMongoClientCoffee() {
        return MongoClients.create(createMongoClientSettings(customMongoProperties.getDoctor()));
    }

    @Bean
    public MongoClient reactiveMongoClientBurger() {
        return MongoClients.create(createMongoClientSettings(customMongoProperties.getPatient()));
    }

    @Primary
    @Bean("mongoTemplateCoffee")
    public ReactiveMongoTemplate reactiveMongoTemplateCoffee(){
        return new ReactiveMongoTemplate(reactiveMongoClientCoffee(),customMongoProperties.getDoctor().getDatabase());
    }
    @Bean("mongoTemplateBurger")
    public ReactiveMongoTemplate reactiveMongoTemplateBurger(){
        return new ReactiveMongoTemplate(reactiveMongoClientBurger(),customMongoProperties.getPatient().getDatabase());
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
