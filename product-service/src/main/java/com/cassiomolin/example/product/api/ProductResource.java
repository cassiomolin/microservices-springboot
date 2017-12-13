package com.cassiomolin.example.product.api;

import com.cassiomolin.example.product.model.Product;
import com.cassiomolin.example.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

/**
 * Product REST resource.
 *
 * @author cassiomolin
 */
@Component
@Path("products")
public class ProductResource {

    @Context
    private UriInfo uriInfo;

    @Autowired
    private ProductService productService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts(@Context HttpHeaders headers) {
        List<Product> products = productService.getProducts();
        return Response.ok(products).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduct(Product product) {
        String id = productService.createProduct(product);
        return Response.created(uriInfo.getAbsolutePathBuilder().path(id).build()).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@PathParam("id") String id) {
        Product product = productService.getProduct(id);
        return Response.ok(product).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteProduct(@PathParam("id") String id) {
        productService.deleteProduct(id);
        return Response.noContent().build();
    }
}