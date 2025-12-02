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
public class ProductRequest {

    // Name of the product sent by the client
    private String name;

    // Description of the product sent by the client
    private String description;

    // Price of the product sent by the client
    private BigDecimal price;
}
