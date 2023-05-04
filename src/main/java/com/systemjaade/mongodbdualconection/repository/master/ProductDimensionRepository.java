package com.systemjaade.mongodbdualconection.repository.master;

import com.systemjaade.mongodbdualconection.model.ProductDimension;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("productDimension")
public interface ProductDimensionRepository extends ReactiveMongoRepository<ProductDimension, String> {
}
