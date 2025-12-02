**Overall Project Architecture**
This project is an Online Shop application built using Spring Boot Microservices and Spring Cloud, where each business domain is developed as an independent, deployable service.

**Core Microservices**
1. Product Service
Manages the product catalog
Provides CRUD operations for products
Uses MongoDB

2. Order Service
Handles order placement
Communicates with Inventory Service and Notification Service
Uses MySQL (via Spring Data JPA)

3. Inventory Service
Checks if products are in stock before an order is placed
Uses MySQL (via Spring Data JPA)

4. Notification Service
Sends email/SMS notifications after an order is successfully placed
Fully stateless (no database)

**Architectural Components & Patterns**
1. Inter-Service Communication
Synchronous: Order → Inventory check using Spring WebClient
Asynchronous: Order → Notification event using Kafka + Spring Kafka

2. Service Orchestration & Resiliency
Service Discovery: Dynamic service lookup via Netflix Eureka
API Gateway: Central entry point with routing & load balancing using Spring Cloud Gateway
Resilience4J:
Circuit Breaker
Time Limiter
Retry

3. Security & Monitoring
Security: OAuth 2.0 Resource Server integrated with Keycloak
Distributed Tracing: Spring Cloud Sleuth + Zipkin
Metrics & Monitoring: Spring Boot Actuator → Prometheus → Grafana

4. Development & Deployment Tools
Testcontainers for integration testing
Docker & Docker Compose for containerized microservices + infra
Jib Maven Plugin for Docker image building without Dockerfile
