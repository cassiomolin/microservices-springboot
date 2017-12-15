package com.cassiomolin.example.shoppinglist.api.mapper;

import com.cassiomolin.example.shoppinglist.api.model.CreateShoppingListPayload;
import com.cassiomolin.example.shoppinglist.api.model.QueryShoppingListResult;
import com.cassiomolin.example.shoppinglist.domain.ShoppingList;
import org.mapstruct.Mapper;

/**
 * Component that maps a domain model to an API model and vice-versa.
 *
 * @author cassiomolin
 */
@Mapper
public interface ShoppingListMapper {

    ShoppingList toShoppingList(CreateShoppingListPayload shoppingListPayload);

    QueryShoppingListResult toQueryShoppingListResult(ShoppingList shoppingList);
}
//    private ShoppingList toShoppingList(CreateShoppingListPayload shoppingListPayload) {
//        ShoppingList shoppingList = new ShoppingList();
//        shoppingList.setName(shoppingListPayload.getName());
//        shoppingList.setItems(shoppingListPayload.getItems().stream().map(item -> {
//            Product product = new Product();
//            product.setId(item.getId());
//            return product;
//        }).collect(Collectors.toSet()));
//        return shoppingList;
//    }
//
//    private QueryShoppingListResult toQueryShoppingListResult(ShoppingList shoppingList) {
//        QueryShoppingListResult queryResult = new QueryShoppingListResult();
//        queryResult.setId(shoppingList.getId());
//        queryResult.setName(shoppingList.getName());
//        queryResult.setCreatedDate(shoppingList.getCreatedDate());
//        queryResult.setItems(shoppingList.getItems().stream().map(product -> {
//            QueryShoppingListResult.Item item = new QueryShoppingListResult.Item();
//            item.setId(product.getId());
//            item.setName(product.getName());
//            return item;
//        }).collect(Collectors.toList()));
//        return queryResult;
//    }
