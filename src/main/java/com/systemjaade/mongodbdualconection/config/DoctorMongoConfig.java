package com.systemjaade.mongodbdualconection.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.systemjaade.mongodbdualconection.repository.doctor",
        reactiveMongoTemplateRef = "mongoTemplateCoffee")
public class DoctorMongoConfig {
}
