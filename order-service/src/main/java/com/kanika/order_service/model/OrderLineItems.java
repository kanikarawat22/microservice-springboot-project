package com.kanika.order_service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity                                          // Marks this class as a JPA entity (represents a DB table)
@Table(name = "t_order_line_items")            // Table name in the database
@Getter                                         // Lombok: generates getter methods
@Setter                                         // Lombok: generates setter methods
@AllArgsConstructor                              // Lombok: constructor with all fields
@NoArgsConstructor                               // Lombok: no-argument constructor
public class OrderLineItems {

    @Id                                          // Primary key for this table
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                              // Auto-generated ID

    private String skuCode;                       // Product SKU (unique product identifier)
    private BigDecimal price;                     // Price of 1 unit of this product
    private Integer quantity;                     // Quantity ordered for this product
}
    