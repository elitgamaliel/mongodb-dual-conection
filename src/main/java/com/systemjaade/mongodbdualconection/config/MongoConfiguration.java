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
    public MongoClient reactiveMongoClientMotorizedRouting() {
        return MongoClients.create(createMongoClientSettings(customMongoProperties.getMotorizedRouting()));
    }

    @Bean
    public MongoClient reactiveMongoClientMaster() {
        return MongoClients.create(createMongoClientSettings(customMongoProperties.getMaster()));
    }

    @Primary
    @Bean("mongoTemplateMotorizedRouting")
    public ReactiveMongoTemplate reactiveMongoTemplateMotorizedRouting(){
        return new ReactiveMongoTemplate(reactiveMongoClientMotorizedRouting(),customMongoProperties.getMotorizedRouting().getDatabase());
    }

    @Bean("mongoTemplateMaster")
    public ReactiveMongoTemplate reactiveMongoTemplateMaster(){
        return new ReactiveMongoTemplate(reactiveMongoClientMaster(),customMongoProperties.getMaster().getDatabase());
    }


    private MongoClientSettings createMongoClientSettings(MongoProperties mongoProperties) {
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
