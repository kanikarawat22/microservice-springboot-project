package com.kanika.inventory_service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

/**
 * Inventory entity represents the 'inventory' table in the database.
 * Each object of this class maps to one row in the inventory table.
 */
@Entity                 // Marks this class as a JPA entity (table in DB)
@Table() 
@Getter
@Setter
@AllArgsConstructor     // Constructor with all fields
@NoArgsConstructor      // Empty constructor required by JPA
public class Inventory {

    /**
     * Primary key of the inventory table.
     * @Id marks it as primary key.
     * @GeneratedValue generates ID automatically.
     *
     * GenerationType.IDENTITY → Auto-increment behavior in most SQL databases.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * SKU code of the product.
     * A unique identifier used to search products.
     */
    private String skuCode;

    /**
     * Available quantity in stock.
     * If quantity > 0 → product is in stock.
     */
    private Integer quantity;
}
