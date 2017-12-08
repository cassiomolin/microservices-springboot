package com.cassiomolin.example.shoppinglist.service;

import com.cassiomolin.example.shoppinglist.model.Product;
import com.cassiomolin.example.shoppinglist.model.ShoppingList;
import com.cassiomolin.example.shoppinglist.repository.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingListService {

//    @Autowired
//    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancer;


    @Autowired
    private ShoppingListRepository shoppingListRepository;

    private Client client;

    @PostConstruct
    private void init() {
        this.client = ClientBuilder.newClient();
    }

    public String createShoppingList(ShoppingList shoppingList) {

        shoppingList.getItems().forEach(product -> {
            if (!checkIfProductExists(product.getId())) {
                throw new RuntimeException("cannot process");
            }
        });

        shoppingList = shoppingListRepository.save(shoppingList);
        return shoppingList.getId();
    }

    public List<ShoppingList> findShoppingLists() {
        List<ShoppingList> shoppingLists = shoppingListRepository.findAll();
        shoppingLists.forEach(shoppingList -> {
            shoppingList.getItems().forEach(product -> {
                Product productDetails = getProductDetails(product.getId());
                product.setName(productDetails.getName());
            });
        });
        return shoppingLists;
    }

    private Product getProductDetails(String productId) {
        URI productServiceUri = getProductServiceUri();
        return client.target(productServiceUri).path("api").path("products").path(productId).request().get(Product.class);
    }

    private boolean checkIfProductExists(String productId) {
        URI productServiceUri = getProductServiceUri();
        Response response = client.target(productServiceUri).path("api").path("products").path(productId).request().head();
        return Response.Status.OK.getStatusCode() == response.getStatus();
    }

    private URI getProductServiceUri() {

        ServiceInstance serviceInstance = loadBalancer.choose("product-service");
        if (serviceInstance == null) {
            throw new RuntimeException("No service available");
        }

        return serviceInstance.getUri();
    }
}