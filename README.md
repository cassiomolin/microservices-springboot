# Sample microservices application with Spring Boot, Zuul, Ribbon and Eureka

Sample microservices application for managing products and shopping lists using:

- **Spring Boot:** Framework for creating standalone Java applications.
- **Jersey:** JAX-RS reference implementation for creating RESTful web services in Java.
- **Jackson:** JSON parser for Java.
- **Zuul:** API gateway.
- **Ribbon:** Load balancer.
- **Eureka:** Service discovery.
- **MongoDB:** NoSQL database based on documents.

This application consists of four different services:

- **product-service:** Provides API for managing products. By default runs on port `8001`.
- **shopping-list-service:** Provides API for managing shopping lists. By default runs on port `8002`.
- **api-gateway:** Sits on the top of the product and shopping list services, providing a gateway for those services. By default runs on port `8765`.
- **service-discovery:** Discovers and registers other service instances. By default runs on port `8761`.