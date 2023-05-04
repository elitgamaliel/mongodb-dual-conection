package com.systemjaade.mongodbdualconection.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class StartLocationIn {
    private BigDecimal latitude;
    private BigDecimal longitude;
}
