package com.kanika.order_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
/**
 * This configuration class creates a WebClient bean.
 * WebClient is used to make HTTP calls to other microservices 
 * (for example, calling Inventory Service from Order Service).
 */
@Configuration  // Marks this class as a configuration provider
public class WebClientConfig {

    /**
     * Creates and returns a WebClient bean.
     * WebClient is a non-blocking, reactive HTTP client.
     *
     * By calling WebClient.builder().build(), we get a default WebClient
     * that can be injected anywhere using @Autowired or constructor injection.
     *
     * Example usage in a service:
     *   webClient.get()
     *            .uri("http://inventory-service/api/inventory")
     *            .retrieve()
     *            .bodyToFlux(...)
     */
    @Bean  // Marks this method as a bean creator in Spring's application context
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}
