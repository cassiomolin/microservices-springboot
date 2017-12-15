package com.cassiomolin.example.product.api;

import com.cassiomolin.example.product.api.model.CreateProductPayload;
import com.cassiomolin.example.product.api.model.QueryProductResult;
import com.cassiomolin.example.product.domain.Product;
import com.cassiomolin.example.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Component that exposes REST API endpoints for products.
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
        List<QueryProductResult> queryResults = products.stream().map(this::toQueryProductResult).collect(Collectors.toList());
        return Response.ok(queryResults).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduct(@Valid @NotNull CreateProductPayload productPayload) {
        Product product = toProduct(productPayload);
        String id = productService.createProduct(product);
        return Response.created(uriInfo.getAbsolutePathBuilder().path(id).build()).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@PathParam("id") String id) {
        Product product = productService.getProduct(id);
        QueryProductResult queryResult = toQueryProductResult(product);
        return Response.ok(queryResult).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteProduct(@PathParam("id") String id) {
        productService.deleteProduct(id);
        return Response.noContent().build();
    }

    private Product toProduct(CreateProductPayload productPayload) {
        Product product = new Product();
        product.setName(productPayload.getName());
        return product;
    }

    private QueryProductResult toQueryProductResult(Product product) {
        QueryProductResult queryResult = new QueryProductResult();
        queryResult.setId(product.getId());
        queryResult.setName(product.getName());
        return queryResult;
    }
}