package com.cassiomolin.example.shoppinglist.api;

import com.cassiomolin.example.shoppinglist.api.mapper.ShoppingListMapper;
import com.cassiomolin.example.shoppinglist.api.model.CreateShoppingListPayload;
import com.cassiomolin.example.shoppinglist.api.model.QueryShoppingListResult;
import com.cassiomolin.example.shoppinglist.domain.ShoppingList;
import com.cassiomolin.example.shoppinglist.service.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
    private ShoppingListMapper shoppingListMapper;

    @Autowired
    private ShoppingListService shoppingListService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getShoppingLists() {
        List<ShoppingList> shoppingLists = shoppingListService.getShoppingLists();
        List<QueryShoppingListResult> queryResults = shoppingLists.stream().map(shoppingListMapper::toQueryShoppingListResult).collect(Collectors.toList());
        return Response.ok(queryResults).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createShoppingList(@Valid @NotNull CreateShoppingListPayload shoppingListPayload) {
        ShoppingList shoppingList = shoppingListMapper.toShoppingList(shoppingListPayload);
        String id = shoppingListService.createShoppingList(shoppingList);
        return Response.created(uriInfo.getAbsolutePathBuilder().path(id).build()).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getShoppingList(@PathParam("id") String id) {
        ShoppingList shoppingList = shoppingListService.getShoppingList(id);
        QueryShoppingListResult queryResult = shoppingListMapper.toQueryShoppingListResult(shoppingList);
        return Response.ok(queryResult).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteShoppingList(@PathParam("id") String id) {
        shoppingListService.deleteShoppingList(id);
        return Response.noContent().build();
    }
}