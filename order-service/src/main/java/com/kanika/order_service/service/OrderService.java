package com.kanika.order_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import io.micrometer.tracing.Tracer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.kanika.order_service.dto.OrderLineItemsDto;
import com.kanika.order_service.dto.OrderRequest;
import com.kanika.order_service.event.OrderPlacedEvent;
import com.kanika.order_service.dto.InventoryResponse;
import com.kanika.order_service.model.Order;
import com.kanika.order_service.model.OrderLineItems;
import com.kanika.order_service.repository.OrderRepository;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor     // Generates constructor for final fields
@Transactional               // Ensures all DB operations run in a transaction
@Slf4j                       // For logging
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;
    private final Tracer tracer;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    /**
     * Main method to place an order.
     * It validates the request, checks inventory, and saves the order.
     */
    public String placeOrder(OrderRequest orderRequest) {

        // ⭐ Step 1: Validate incoming line items (must not be empty)
        List<OrderLineItemsDto> items = orderRequest.getOrderLineItemsDtoList();
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one line item");
        }

        // ⭐ Step 2: Create a new Order entity with a random order number
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        // ⭐ Step 3: Convert DTO objects → Entity objects
        List<OrderLineItems> orderLineItems = items.stream()
                .map(this::mapToDto)
                .toList();

        // Add the converted list to the Order object
        order.setOrderLineItemsList(orderLineItems);

        // Extract SKU codes to check inventory
        List<String> skuCodes = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getSkuCode)    
                .toList();

        

        // ⭐ Step 4: Call Inventory Service to check stock
        InventoryResponse[] inventoryResponseArray = webClientBuilder.build().get()
                .uri("http://inventory-service/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();  // Block to wait for synchronous response

        // ⭐ Step 5: Check if all products are available in stock
        boolean allProductsInStock =
                Arrays.stream(inventoryResponseArray).allMatch(InventoryResponse::isInStock);

        if (allProductsInStock) {
            // ⭐ Step 6: Save the order since inventory is available
            orderRepository.save(order);
            kafkaTemplate.send("notificationTopic", new OrderPlacedEvent(order.getOrderNumber()));
            return "Order Placed Sucessfully";
        } else {
            // Throw error if ANY product is out of stock
            throw new IllegalArgumentException("Product is not in stock, please try again later");
        }
    }

    /**
     * Helper method:
     * Converts OrderLineItemsDto (from request) into OrderLineItems entity (for DB).
     */
    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
