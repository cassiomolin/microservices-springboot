package com.cassiomolin.example.product.api;

import com.cassiomolin.example.commons.api.validation.groups.Create;
import com.cassiomolin.example.product.api.model.ProductDetails;
import com.cassiomolin.example.product.domain.Product;
import com.cassiomolin.example.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.executable.ValidateOnExecution;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
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
        List<ProductDetails> productDetails = products.stream().map(this::toProductDetails).collect(Collectors.toList());
        return Response.ok(productDetails).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduct(@Valid @ConvertGroup(from = Default.class, to = Create.class) @NotNull ProductDetails productDetails) {
        Product product = toProduct(productDetails);
        String id = productService.createProduct(product);
        return Response.created(uriInfo.getAbsolutePathBuilder().path(id).build()).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@PathParam("id") String id) {
        Product product = productService.getProduct(id);
        ProductDetails productDetails = toProductDetails(product);
        return Response.ok(productDetails).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteProduct(@PathParam("id") String id) {
        productService.deleteProduct(id);
        return Response.noContent().build();
    }

    private Product toProduct(ProductDetails productDetails) {
        Product product = new Product();
        product.setId(productDetails.getId());
        product.setName(productDetails.getName());
        return product;
    }

    private ProductDetails toProductDetails(Product product) {
        ProductDetails productDetails = new ProductDetails();
        productDetails.setId(product.getId());
        productDetails.setName(product.getName());
        return productDetails;
    }
}