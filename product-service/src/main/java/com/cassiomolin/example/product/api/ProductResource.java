package com.cassiomolin.example.product.api;

import com.cassiomolin.example.product.model.Product;
import com.cassiomolin.example.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Path("products")
public class ProductResource {

    @Autowired
    private ProductService productService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createShoppingList(Product product) {
        String id = productService.createProduct(product);
        return Response.ok(id).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts(@Context HttpHeaders headers) {List<Product> products = productService.findProducts();
        return Response.ok(products).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@PathParam("id") String id) {
        Optional<Product> optionalProduct = productService.findProduct(id);
        if (optionalProduct.isPresent()) {
            return Response.ok(optionalProduct.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}