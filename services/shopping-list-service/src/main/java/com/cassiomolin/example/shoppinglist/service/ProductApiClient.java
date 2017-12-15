package com.cassiomolin.example.shoppinglist.service;

import com.cassiomolin.example.shoppinglist.config.CachingConfiguration;
import com.cassiomolin.example.shoppinglist.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.ws.rs.ServiceUnavailableException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Optional;

/**
 * REST API client for the product service.
 *
 * @author cassiomolin
 */
@Component
class ProductApiClient {

    private Client client;

    @Autowired
    private LoadBalancerClient loadBalancer;

    private static final String PRODUCT_SERVICE = "product-service";

    @PostConstruct
    private void init() {
        this.client = ClientBuilder.newClient();
    }

    @Cacheable(cacheNames = CachingConfiguration.PRODUCTS_CACHE, key = "#productId")
    public Optional<Product> getProduct(String productId) {
        URI productServiceUri = getProductServiceUri();
        Response response = client.target(productServiceUri).path("api").path("products").path(productId).request().get();
        if (Response.Status.Family.SUCCESSFUL == response.getStatusInfo().getFamily()) {
            return Optional.ofNullable(response.readEntity(Product.class));
        } else {
            return Optional.empty();
        }
    }

    public boolean checkIfProductExists(String productId) {
        URI productServiceUri = getProductServiceUri();
        Response response = client.target(productServiceUri).path("api").path("products").path(productId).request().head();
        return Response.Status.OK.getStatusCode() == response.getStatus();
    }

    private URI getProductServiceUri() {

        ServiceInstance serviceInstance = loadBalancer.choose(PRODUCT_SERVICE);
        if (serviceInstance == null) {
            throw new ServiceUnavailableException("Service unavailable");
        }

        return serviceInstance.getUri();
    }
}
