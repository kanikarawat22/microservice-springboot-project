package com.kanika.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data                                           // Lombok: generates getters, setters, toString, equals, hashCode
@AllArgsConstructor                             // Lombok: constructor with all fields
@NoArgsConstructor                              // Lombok: no-argument constructor
public class OrderLineItemsDto {

    private Long id;                             // ID of the line item (not always required for creating new orders)
    private String skuCode;                      // Product SKU code (unique identifier for products)
    private BigDecimal price;                    // Price of a single unit of this product
    private Integer quantity;                    // Number of units the user wants to order
}
