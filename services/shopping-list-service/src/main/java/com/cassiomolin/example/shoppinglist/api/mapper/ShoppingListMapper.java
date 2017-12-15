package com.cassiomolin.example.shoppinglist.api.mapper;

import com.cassiomolin.example.shoppinglist.api.model.CreateOrUpdateShoppingListPayload;
import com.cassiomolin.example.shoppinglist.api.model.QueryShoppingListResult;
import com.cassiomolin.example.shoppinglist.domain.ShoppingList;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * Component that maps a domain model to an API model and vice-versa.
 *
 * @author cassiomolin
 */
@Mapper
public interface ShoppingListMapper {

    ShoppingList toShoppingList(CreateOrUpdateShoppingListPayload payload);

    QueryShoppingListResult toQueryShoppingListResult(ShoppingList shoppingList);

    void updateShoppingList(CreateOrUpdateShoppingListPayload payload, @MappingTarget ShoppingList shoppingList);
}
