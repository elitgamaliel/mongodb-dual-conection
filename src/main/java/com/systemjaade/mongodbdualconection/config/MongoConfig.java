package com.systemjaade.mongodbdualconection.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.systemjaade.mongodbdualconection.repository")
public class MongoConfig extends AbstractReactiveMongoConfiguration {
    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    @Value("${spring.data.mongodb.second.uri}")
    private String secondMongoUri;

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(mongoUri);
    }

    @Bean(name = "secondMongoClient")
    public MongoClient secondMongoClient() {
        return MongoClients.create(secondMongoUri);
    }

    @Bean
    @Primary
    public ReactiveMongoDatabaseFactory mongoDatabaseFactory() {
        return new SimpleReactiveMongoDatabaseFactory(mongoClient(), getDatabaseName());
    }

    @Bean(name = "secondMongoDatabaseFactory")
    public ReactiveMongoDatabaseFactory secondMongoDatabaseFactory() {
        return new SimpleReactiveMongoDatabaseFactory(secondMongoClient(), getDatabaseName());
    }

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate() {
        return new ReactiveMongoTemplate(mongoDatabaseFactory());
    }

    @Bean(name = "secondReactiveMongoTemplate")
    public ReactiveMongoTemplate secondReactiveMongoTemplate() {
        return new ReactiveMongoTemplate(secondMongoDatabaseFactory());
    }

    @Override
    protected String getDatabaseName() {
        return "patient";
    }
}
