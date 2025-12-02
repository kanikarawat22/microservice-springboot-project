package com.kanika.product_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.kanika.product_service.dto.ProductRequest;
import com.kanika.product_service.dto.ProductResponse;
import com.kanika.product_service.model.Product;
import com.kanika.product_service.repository.ProductRepository;

import java.util.List;

@Service // Marks this class as a Spring service (business logic layer)
@RequiredArgsConstructor // Automatically generates constructor for final fields
@Slf4j // Enables logging using log.info(), log.error(), etc.
public class ProductService {

    // Repository to interact with the database (CRUD operations)
    private final ProductRepository productRepository;

    // Method to create/save a new product
    public void createProduct(ProductRequest productRequest) {

        // Converting ProductRequest DTO into Product entity
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        // Saving the product into the database
        productRepository.save(product);

        // Logging the saved product ID
        log.info("Product {} is saved", product.getId());
    }

    // Method to fetch all products from the database
    public List<ProductResponse> getAllProducts() {

        // Getting all products as a list of Product entities
        List<Product> products = productRepository.findAll();

        // Converting Product entity list into ProductResponse DTO list
        return products.stream()
                .map(this::mapToProductResponse)
                .toList();
    }

    // Helper method to convert Product entity → ProductResponse DTO
    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
