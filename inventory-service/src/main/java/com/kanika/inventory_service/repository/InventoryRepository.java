package com.kanika.inventory_service.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kanika.inventory_service.model.Inventory;

/**
 * InventoryRepository interacts with the database.
 * It extends JpaRepository which gives basic CRUD methods:
 * - save()
 * - findById()
 * - findAll()
 * - delete()
 * - count()
 * and many more.
 *
 * This repository contains custom methods to query the Inventory table.
 */
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    /**
     * Custom finder method.
     * Spring Data JPA automatically generates SQL based on method name.
     *
     * Method name: findBySkuCodeIn
     * Meaning:
     *   "Find all Inventory records where skuCode is IN the given list"
     *
     * @param skuCode List of SKU codes
     * @return List<Inventory> matching those SKU codes
     */
    List<Inventory> findBySkuCodeIn(List<String> skuCode);
}
