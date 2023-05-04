package com.systemjaade.mongodbdualconection.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "patient")
public class Patient {
    @Id
    private String id;
    private String name;
    private int age;

}
