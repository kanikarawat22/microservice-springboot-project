package com.kanika.product_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.kanika.product_service.model.Product;

// Repository interface to perform CRUD operations on Product collection in MongoDB
public interface ProductRepository extends MongoRepository<Product, String> {
    
    // No need to write any code here.
    // MongoRepository automatically provides all basic operations like:
    // save(), findAll(), findById(), deleteById(), etc.
}
