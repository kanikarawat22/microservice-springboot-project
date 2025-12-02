package com.kanika.product_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.kanika.product_service.dto.ProductRequest;
import com.kanika.product_service.dto.ProductResponse;
import com.kanika.product_service.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/product") // Base URL for all product-related APIs
@RequiredArgsConstructor // Generates constructor for final fields (Dependency Injection)
public class ProductController {

    // Injecting ProductService to handle business logic
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Returns HTTP status 201 when product is created
    public void createProduct(@RequestBody ProductRequest productRequest) {

        // Calling the service method to create/save a product
        productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK) // Returns HTTP status 200 on successful fetch
    public List<ProductResponse> getAllProducts() {

        // Calling the service method to get all products and returning the list
        return productService.getAllProducts();
    }

}
