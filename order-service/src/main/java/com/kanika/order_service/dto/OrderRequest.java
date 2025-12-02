package com.kanika.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data                                          // Lombok: generates getters, setters, toString, equals, hashCode
@AllArgsConstructor                            // Lombok: constructor with all fields
@NoArgsConstructor                             // Lombok: no-argument constructor
public class OrderRequest {

    // List of product items coming from the client (API request)
    // Each item contains SKU, price, and quantity
    private List<OrderLineItemsDto> orderLineItemsDtoList;
}
