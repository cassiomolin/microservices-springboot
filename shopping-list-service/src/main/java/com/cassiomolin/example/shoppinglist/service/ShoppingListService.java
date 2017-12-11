package com.cassiomolin.example.shoppinglist.service;

import com.cassiomolin.example.shoppinglist.model.Product;
import com.cassiomolin.example.shoppinglist.model.ShoppingList;
import com.cassiomolin.example.shoppinglist.repository.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.stream.annotation.StreamListener;
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

                Optional<Product> productDetails = getProductDetails(product.getId());

                String name;
                if (productDetails.isPresent()) {
                    name = productDetails.get().getName();
                } else {
                    name = null;
                }

                product.setName(name);
            });
        });

        return shoppingLists;
    }

    private Optional<Product> getProductDetails(String productId) {
        URI productServiceUri = getProductServiceUri();
        Response response = client.target(productServiceUri).path("api").path("products").path(productId).request().get();
        if (Response.Status.Family.SUCCESSFUL == response.getStatusInfo().getFamily()) {
            return Optional.ofNullable(response.readEntity(Product.class));
        } else {
            return Optional.empty();
        }
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

    @StreamListener(ProductDeletedInput.PRODUCT_DELETED_INPUT)
    public void handleDeletedProduct(Product product) {
        shoppingListRepository.deleteProductsById(product.getId());
    }
}