package com.systemjaade.mongodbdualconection.controller;

import com.systemjaade.mongodbdualconection.model.ProductDimension;
import com.systemjaade.mongodbdualconection.service.ProductDimensionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ProductDimensionController {

    private final ProductDimensionService productDimensionService;

    public ProductDimensionController(ProductDimensionService productDimensionService) {
        this.productDimensionService = productDimensionService;
    }

    @GetMapping("/prodctDimesions")
    public Flux<ProductDimension> findAll() {
        return productDimensionService.findAll();
    }

    @GetMapping("/productDimensions/{codInka}")
    public Mono<ProductDimension> findById(@PathVariable String codInka) {
        System.out.println("id: "+codInka);
        return productDimensionService.findById(codInka);
    }

}
