package com.cassiomolin.example.shoppinglist.api;

import com.cassiomolin.example.commons.api.validation.groups.Create;
import com.cassiomolin.example.shoppinglist.api.model.ShoppingListDetails;
import com.cassiomolin.example.shoppinglist.domain.Product;
import com.cassiomolin.example.shoppinglist.domain.ShoppingList;
import com.cassiomolin.example.shoppinglist.service.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Component that exposes REST API endpoints for shopping lists.
 *
 * @author cassiomolin
 */
@Component
@Path("shopping-lists")
public class ShoppingListResource {

    @Context
    private UriInfo uriInfo;

    @Autowired
    private ShoppingListService shoppingListService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getShoppingLists() {
        List<ShoppingList> shoppingLists = shoppingListService.getShoppingLists();
        List<ShoppingListDetails> shoppingListDetails = shoppingLists.stream().map(this::toShoppingListDetails).collect(Collectors.toList());
        return Response.ok(shoppingListDetails).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createShoppingList(@Valid @ConvertGroup(from = Default.class, to = Create.class) @NotNull ShoppingListDetails shoppingListDetails) {
        ShoppingList shoppingList = toShoppingList(shoppingListDetails);
        String id = shoppingListService.createShoppingList(shoppingList);
        return Response.created(uriInfo.getAbsolutePathBuilder().path(id).build()).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getShoppingList(@PathParam("id") String id) {
        ShoppingList shoppingList = shoppingListService.getShoppingList(id);
        ShoppingListDetails shoppingListDetails = toShoppingListDetails(shoppingList);
        return Response.ok(shoppingListDetails).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteShoppingList(@PathParam("id") String id) {
        shoppingListService.deleteShoppingList(id);
        return Response.noContent().build();
    }

    private ShoppingList toShoppingList(ShoppingListDetails shoppingListDetails) {
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setName(shoppingListDetails.getName());
        shoppingList.setItems(shoppingListDetails.getItems().stream().map(productDetails -> {
            Product product = new Product();
            product.setId(productDetails.getId());
            return product;
        }).collect(Collectors.toSet()));
        return shoppingList;
    }

    private ShoppingListDetails toShoppingListDetails(ShoppingList shoppingList) {
        ShoppingListDetails shoppingListDetails = new ShoppingListDetails();
        shoppingListDetails.setId(shoppingList.getId());
        shoppingListDetails.setName(shoppingList.getName());
        shoppingListDetails.setItems(shoppingList.getItems().stream().map(product -> {
            ShoppingListDetails.ProductDetails productDetails = new ShoppingListDetails.ProductDetails();
            productDetails.setId(product.getId());
            productDetails.setName(product.getName());
            return productDetails;
        }).collect(Collectors.toList()));
        return shoppingListDetails;
    }
}