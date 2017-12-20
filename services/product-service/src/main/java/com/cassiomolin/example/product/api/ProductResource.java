package com.cassiomolin.example.product.api;

import com.cassiomolin.example.product.api.mapper.ProductMapper;
import com.cassiomolin.example.product.api.model.CreateOrUpdateProductPayload;
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
    private ProductMapper productMapper;

    @Autowired
    private ProductService productService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts() {
        List<Product> products = productService.getProducts();
        List<QueryProductResult> queryResults = products.stream().map(productMapper::toQueryProductResult).collect(Collectors.toList());
        return Response.ok(queryResults).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduct(@Valid @NotNull CreateOrUpdateProductPayload productPayload) {
        Product product = productMapper.toProduct(productPayload);
        String id = productService.createProduct(product);
        return Response.created(uriInfo.getAbsolutePathBuilder().path(id).build()).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@PathParam("id") String id) {
        Product product = productService.getProduct(id);
        QueryProductResult queryResult = productMapper.toQueryProductResult(product);
        return Response.ok(queryResult).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProduct(@PathParam("id") String id, @Valid @NotNull CreateOrUpdateProductPayload payload) {
        Product product = productService.getProduct(id);
        productMapper.updateProduct(payload, product);
        productService.updateProduct(product);
        return Response.noContent().build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteProduct(@PathParam("id") String id) {
        productService.deleteProduct(id);
        return Response.noContent().build();
    }
}