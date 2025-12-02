package com.kanika.product_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(value = "product") // Marks this class as a MongoDB document stored in the "product" collection
@AllArgsConstructor           // Generates a constructor with all fields
@NoArgsConstructor            // Generates a default empty constructor
@Builder                      // Enables the builder pattern for creating Product objects
@Data                         // Generates getters, setters, toString, equals, hashCode
public class Product {

    @Id                       // Marks this as the primary key for the MongoDB document
    private String id;

    // Name of the product
    private String name;

    // Description of the product
    private String description;

    // Price of the product (BigDecimal is used for accurate currency values)
    private BigDecimal price;
}
