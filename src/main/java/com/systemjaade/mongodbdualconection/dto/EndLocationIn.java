package com.systemjaade.mongodbdualconection.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EndLocationIn {
    private BigDecimal latitude;
    private BigDecimal longitude;
}
