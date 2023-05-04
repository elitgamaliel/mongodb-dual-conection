package com.systemjaade.mongodbdualconection.service;

import com.systemjaade.mongodbdualconection.model.RoutingWave;
import com.systemjaade.mongodbdualconection.repository.motorizedRouting.RoutingWaveRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class RoutingWaveService {

    private final RoutingWaveRepository routingWaveRepository;

    public RoutingWaveService(RoutingWaveRepository routingWaveRepository) {
        this.routingWaveRepository = routingWaveRepository;
    }

    public Flux<RoutingWave> findAll() {
        return routingWaveRepository.findAll();
    }
}
