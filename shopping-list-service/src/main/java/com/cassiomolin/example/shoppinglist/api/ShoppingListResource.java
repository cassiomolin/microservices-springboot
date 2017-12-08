package com.cassiomolin.example.shoppinglist.api;

import com.cassiomolin.example.shoppinglist.model.ShoppingList;
import com.cassiomolin.example.shoppinglist.service.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Path("shopping-lists")
public class ShoppingListResource {

    @Autowired
    private ShoppingListService shoppingListService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createShoppingList(ShoppingList shoppingList) {
        String id = shoppingListService.createShoppingList(shoppingList);
        return Response.ok(id).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getShoppingLists() {
        List<ShoppingList> shoppingLists = shoppingListService.findShoppingLists();
        return Response.ok(shoppingLists).build();
    }
}