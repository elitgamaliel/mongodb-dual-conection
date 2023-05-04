package com.systemjaade.mongodbdualconection.controller;

import com.systemjaade.mongodbdualconection.model.RoutingWave;
import com.systemjaade.mongodbdualconection.service.RoutingWaveService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class RoutingWaveController {
    private final RoutingWaveService routingWaveService;

    public RoutingWaveController(RoutingWaveService routingWaveService) {
        this.routingWaveService = routingWaveService;
    }

    @GetMapping("/routingWave")
    public Flux<RoutingWave> findAll() {
        return routingWaveService.findAll();
    }
}
