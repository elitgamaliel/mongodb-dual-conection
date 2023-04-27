package com.systemjaade.mongodbdualconection.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

@Configuration
public class MongoConfig extends AbstractReactiveMongoConfiguration {

    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    @Value("${spring.data.mongodb.second.uri}")
    private String secondMongoUri;

    @Value("${spring.data.mongodb.database}")
    private String databaseName;

    @Value("${spring.data.mongodb.second.database}")
    private String secondDatabaseName;

    @Bean(name = "mongoDatabaseFactory")
    public MongoDatabaseFactory mongoDatabaseFactory() {
        MongoClient mongoClient = MongoClients.create(mongoUri);
        return (MongoDatabaseFactory) new SimpleReactiveMongoDatabaseFactory(mongoClient, databaseName);
    }

    @Bean(name = "secondMongoDatabaseFactory")
    public MongoDatabaseFactory secondMongoDatabaseFactory() {
        MongoClient mongoClient = MongoClients.create(secondMongoUri);
        return (MongoDatabaseFactory) new SimpleReactiveMongoDatabaseFactory(mongoClient, secondDatabaseName);
    }

    @Bean(name = "mongoTemplate")
    public ReactiveMongoTemplate mongoTemplate() throws Exception {
        return new ReactiveMongoTemplate((ReactiveMongoDatabaseFactory) mongoDatabaseFactory());
    }

    @Bean(name = "secondMongoTemplate")
    public ReactiveMongoTemplate secondMongoTemplate() throws Exception {
        return new ReactiveMongoTemplate((ReactiveMongoDatabaseFactory) secondMongoDatabaseFactory());
    }

    @Override
    protected String getDatabaseName() {
        return databaseName;
    }

    @Override
    public MongoClient reactiveMongoClient() {
        return MongoClients.create(mongoUri);
    }

    @Bean
    public MappingMongoConverter mappingMongoConverter() throws Exception {
        MongoMappingContext mappingContext = new MongoMappingContext();
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDatabaseFactory());
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mappingContext);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return converter;
    }

}
