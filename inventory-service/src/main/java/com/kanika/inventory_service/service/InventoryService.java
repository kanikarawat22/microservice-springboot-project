package com.kanika.inventory_service.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.kanika.inventory_service.dto.InventoryResponse;
import com.kanika.inventory_service.repository.InventoryRepository;

/**
 * This service contains all the business logic related to Inventory.
 * It communicates with the InventoryRepository to fetch product stock details.
 */
@Service
@RequiredArgsConstructor // Creates a constructor for the final repository field
@Slf4j // Enables logging (log.info, log.error etc.)
public class InventoryService {

    private final InventoryRepository inventoryRepository;


    /**
     * Checks if products (given by SKU codes) are in stock.
     *
     * @Transactional(readOnly = true)
     * → Marks this method as a read-only transaction, meaning:
     *   - No data will be modified
     *   - Faster performance for SELECT queries
     *
     * @param skuCode List of SKU codes to check
     * @return List<InventoryResponse> containing SKU code and stock status
     */
    @Transactional(readOnly = true)
    @SneakyThrows
    
    public List<InventoryResponse> isInStock(List<String> skuCode) {

        // Find all inventory records matching the provided list of SKU codes
        // findBySkuCodeIn(skuCode) returns List<Inventory>

        log.info("Wait Started");
        Thread.sleep(10000);
        log.info("Wait Ended");
        
        return inventoryRepository.findBySkuCodeIn(skuCode)
                .stream()

                // For each Inventory item, convert it to InventoryResponse DTO
                .map(inventory ->
                        InventoryResponse.builder()
                                .skuCode(inventory.getSkuCode()) // Copy SKU code
                                .isInStock(inventory.getQuantity() > 0) // Product is in stock if quantity > 0
                                .build()
                )

                // Convert Stream → List
                .toList();
    }
}
