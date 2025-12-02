package com.kanika.product_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data                    // Generates getters, setters, toString, equals, hashCode
@Builder                 // Enables builder pattern for creating objects
@AllArgsConstructor      // Constructor with all fields
@NoArgsConstructor       // Default no-argument constructor
public class ProductResponse {

    // Unique ID of the product (returned to the client)
    private String id;

    // Name of the product
    private String name;

    // Description of the product
    private String description;

    // Price of the product
    private BigDecimal price;
}
