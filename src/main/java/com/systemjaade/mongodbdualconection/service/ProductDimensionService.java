package com.systemjaade.mongodbdualconection.service;

import com.systemjaade.mongodbdualconection.model.ProductDimension;
import com.systemjaade.mongodbdualconection.repository.master.ProductDimensionRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductDimensionService {
    private final ProductDimensionRepository productDimensionRepository;

    public ProductDimensionService(ProductDimensionRepository productDimensionRepository) {
        this.productDimensionRepository = productDimensionRepository;
    }

    public Flux<ProductDimension> findAll() {
        return productDimensionRepository.findAll();
    }

}
