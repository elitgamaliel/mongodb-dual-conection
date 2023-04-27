package com.systemjaade.mongodbdualconection.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mapping.model.SimpleTypeHolder;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.ReactiveMongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableReactiveMongoRepositories(basePackages = "com.systemjaade.mongodbdualconection.repository", reactiveMongoTemplateRef = "reactivePatientMongoTemplate")
public class MongoConfig extends AbstractReactiveMongoConfiguration {

    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    @Value("${spring.data.mongodb.second.uri}")
    private String secondMongoUri;

    @Override
    protected String getDatabaseName() {
        return mongoUri;
    }

    @Bean
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString(mongoUri);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoClient secondMongoClient() {
        ConnectionString connectionString = new ConnectionString(secondMongoUri);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public ReactiveMongoTemplate reactivePatientMongoTemplate() throws Exception {
        return new ReactiveMongoTemplate(reactiveMongoDbFactory(), mappingMongoConverter());
    }

    @Bean
    public ReactiveMongoTemplate reactiveDoctorMongoTemplate() throws Exception {
        return new ReactiveMongoTemplate(reactiveDoctorMongoDbFactory(), mappingMongoConverter());
    }

    @Bean
    public SimpleMongoClientDatabaseFactory mongoClientFactory() {
        return new SimpleMongoClientDatabaseFactory("mongodb://localhost:27017");
    }

    @Bean
    public ReactiveMongoDatabaseFactory mongoDatabaseFactory() {
        return new SimpleReactiveMongoDatabaseFactory((MongoClient) mongoClientFactory(), "patient");
    }

    @Bean
    public ReactiveMongoDatabaseFactory secondMongoDatabaseFactory() {
        return new SimpleReactiveMongoDatabaseFactory((MongoClient) mongoClientFactory(), "doctor");
    }

    @Bean
    public SimpleTypeHolder typeHolder() {
        return new SimpleTypeHolder();
    }

    @Bean
    public MappingMongoConverter mappingMongoConverter() throws Exception {
        MongoMappingContext mappingContext = new MongoMappingContext();
        mappingContext.setSimpleTypeHolder(typeHolder());
        MappingMongoConverter converter = new MappingMongoConverter(mongoDatabaseFactory(), mappingContext);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return converter;
    }


    @Bean
    public ReactiveMongoDatabaseFactory reactiveMongoDbFactory() {
        return new SimpleReactiveMongoDatabaseFactory(mongoClient(), mongoUri);
    }

    @Bean
    public ReactiveMongoDatabaseFactory reactiveDoctorMongoDbFactory() {
        return new SimpleReactiveMongoDatabaseFactory(secondMongoClient(), secondMongoUri);
    }

    @Bean
    public ReactiveTransactionManager reactiveTransactionManager() {
        return new ReactiveMongoTransactionManager(reactiveMongoDbFactory());
    }

    @Bean
    public ReactiveTransactionManager reactiveDoctorTransactionManager() {
        return new ReactiveMongoTransactionManager(reactiveDoctorMongoDbFactory());
    }

}
