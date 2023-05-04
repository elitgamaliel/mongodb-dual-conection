package com.systemjaade.mongodbdualconection.repository.motorizedRouting;

import com.systemjaade.mongodbdualconection.model.RoutingWave;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("routingWave")
public interface RoutingWaveRepository extends ReactiveMongoRepository<RoutingWave,String> {
}
