package com.systemjaade.mongodbdualconection.model;

import com.systemjaade.mongodbdualconection.dto.TaskDto;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document(collection = "routingWave")
public class RoutingWave implements Serializable {
    @Id
    private String id;
    private String waveId;
    private String runDate;
    private String runResult;
    private String motorizedId;
    private String[] orders;
    private TaskDto[] taskDto;
    private String routeId;
}
