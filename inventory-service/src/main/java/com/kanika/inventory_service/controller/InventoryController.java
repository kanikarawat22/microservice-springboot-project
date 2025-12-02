package com.kanika.inventory_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam; // Used to read query parameters from URL
import java.util.List;
import com.kanika.inventory_service.dto.InventoryResponse;
import com.kanika.inventory_service.service.InventoryService;

import lombok.RequiredArgsConstructor;

/**
 * This controller handles all HTTP requests related to Inventory.
 * It exposes an endpoint to check whether products (by skuCode) are in stock.
 */
@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor // Generates a constructor for the final field (inventoryService)
public class InventoryController {

    // InventoryService is used to perform business logic and fetch stock info
    private final InventoryService inventoryService;

    /**
     * GET Endpoint: /api/inventory?skuCode=abc&skuCode=xyz
     * ----------------------------------------------------
     * @RequestParam List<String> skuCode
     * → Reads multiple skuCode values from the URL query parameters.
     *
     * Example call:
     *     http://localhost:8082/api/inventory?skuCode=iphone_13&skuCode=macbook_air
     *
     * Returns a list of InventoryResponse objects for all requested SKU codes.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) {
        // Passing the list of skuCodes to the service layer to check stock availability
        return inventoryService.isInStock(skuCode); 
    }
}
