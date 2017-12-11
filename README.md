# Sample microservices application with Spring Boot, Zuul, Eureka and MongoDB

[![Build Status](https://travis-ci.org/cassiomolin/microservices-springboot.svg?branch=master)](https://travis-ci.org/cassiomolin/microservices-springboot)
[![MIT Licensed](https://img.shields.io/badge/license-MIT-blue.svg)](https://raw.githubusercontent.com/cassiomolin/microservices-springboot/master/LICENSE.txt)

Sample microservices application for managing products and shopping lists using:

- **Spring Boot:** Framework for creating standalone Java applications.
- **Jersey:** JAX-RS reference implementation for creating RESTful web services in Java.
- **Jackson:** JSON parser for Java.
- **Netflix Zuul:** API gateway.
- **Netflix Eureka:** Service discovery.
- **MongoDB:** NoSQL database based on documents.

This application consists of four different services:

- **Product service:** Provides API for managing products. By default runs on port `8001`.
- **Shopping list service:** Provides API for managing shopping lists. By default runs on port `8002`.
- **Service discovery:** Netflix Eureka service that discovers and registers other service instances. By default runs on port `8761`.
- **API gateway:** Netflix Zuul API gateway that sits on the top of the product and shopping list services, providing a gateway for those services. By default runs on port `8765`.

See the diagram below:

<!-- Hack to center the image in GitHub -->
<p align="center">
  <img src="architecture-diagram.png" alt="Architecture diagram" width="50%"/>
</p>

## External services

This application depends on external services. Ensure that these services are up and running when attempting to run the application.

- **MongoDB:** Shopping and product services use MongoDB for persistence. Ensure that you have a MongoDB instance running on `localhost` port `27017` (default port). The `product` and `shopping-list` databases will be created by the application.
- **RabbitMQ:** RabbitMQ is used as message broker. Ensure that RabbitMQ is running on `localhost` port `5672` (default port).

## Building and running this application

To build and run this application, follow these steps:

1. Open a command line window or terminal.
1. Navigate to the root directory of the project, where the `pom.xml` resides.
1. Compile the project: `mvn clean compile`.
1. Package the application: `mvn package`.
1. Change into the `target` directory of the `dist` module: `cd dist/target`
1. You should see a folder with the following or a similar name: `microservices-1.0`. Change into this folder: `cd microservices-1.0`
1. Start the services as indicated below:

### Running the service discovery application

1. Open a command line window or terminal.
1. Start the `service-discovery` application: `java -jar service-discovery-1.0.jar`
1. A Netflix Eureka console will be available at `http://localhost:8761`.

### Running the product service application

1. Open a command line window or terminal.
1. Start the `product-service` application: `java -jar product-service-1.0.jar`
1. This service will start on the port `8001` and it will automatically register itself in the service-discovery. Check the Eureka console.

### Running the shopping list service application

1. Open a command line window or terminal.
1. Start the `shopping-list-service` application: `java -jar shopping-list-service-1.0.jar`
1. This service will start on the port `8002` and it will automatically register itself in the service-discovery. Check the Eureka console.

### Running the API gateway application

1. Open a command line window or terminal.
1. Start the `api-gateway` application: `java -jar api-gateway-1.0.jar`

### Running extra instances (optional)

If you want to, you can run extra instances of `product-service` and `shopping-list-service` applications, just use a different port: `java -DPORT=8003 -jar product-service-1.0.jar`. New instances will automatically register themselves in the service-discovery.

Requests coming from the `api-gateway` service will balance the requests between the instances.

## REST API overview

The application provides a REST API for managing tasks. See the [curl][] scripts below with the supported operations:

### Get all products

```bash
curl -X GET \
  'http://localhost:8765/api/products' \
  -H 'Accept: application/json'
```

### Create a product

```bash
curl -X POST \
  'http://localhost:8765/api/products' \
  -H 'Content-Type: application/json' \
  -d '{
  "name": "Avocado"
}'
```

### Get a product by id

```bash
curl -X GET \
  'http://localhost:8765/api/products/<product-id>' \
  -H 'Accept: application/json'
```

### Delete a product by id

```bash
curl -X DELETE \
  'http://localhost:8765/api/products/<product-id>'
```

### Create a shopping list

```bash
curl -X POST \
  'http://localhost:8765/api/shopping-lists' \
  -H 'Content-Type: application/json' \
  -d '{
  "name": "Birthday party",
  "items": [
    {
      "id": "<product-id>"
    },
    {
      "id": "<product-id>"
    },
    ...
  ]
}'
```

### Get all shopping lists

```bash
curl -X GET \
  'http://localhost:8765/api/shopping-lists' \
  -H 'Accept: application/json'
```
