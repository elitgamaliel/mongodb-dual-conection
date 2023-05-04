package com.systemjaade.mongodbdualconection.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Document(collection = "productDimension")
public class ProductDimension implements Serializable {
    @Id
    private String codInka;
    private String description;
    private Boolean fractionable;
    private BigDecimal volume;
    private int umv;
}
