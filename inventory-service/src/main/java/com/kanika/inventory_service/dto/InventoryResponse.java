package com.kanika.inventory_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * InventoryResponse is a Data Transfer Object (DTO).
 * This class is used to send inventory information back to the client
 * or other microservices (like Order Service).
 *
 * It contains only the required fields needed in the response,
 * not the entire Inventory entity.
 */
@Data // Generates getters, setters, toString, equals, hashCode
@AllArgsConstructor // Creates constructor with all fields
@NoArgsConstructor  // Creates default empty constructor
@Builder            // Enables builder pattern: InventoryResponse.builder().build()
public class InventoryResponse {

    /**
     * The SKU code of the product.
     * (SKU = Stock Keeping Unit, unique code for each product)
     */
    private String skuCode;

    /**
     * Indicates whether the product is available in stock.
     * True  => quantity > 0
     * False => quantity == 0
     */
    private boolean isInStock;
}
