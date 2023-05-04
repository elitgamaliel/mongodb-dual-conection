package com.systemjaade.mongodbdualconection.dto;

import lombok.Data;

@Data
public class TaskDto {
    private String taskType;
    private StartLocationIn startLocation;
    private EndLocationIn endLocation;
    private Integer sequence;
    private String startDate;
    private String endDate;
    private String localCode;
    private Long orderId;

}
