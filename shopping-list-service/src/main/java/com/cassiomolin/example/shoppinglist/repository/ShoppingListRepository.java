package com.cassiomolin.example.shoppinglist.repository;

import com.cassiomolin.example.shoppinglist.model.ShoppingList;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShoppingListRepository extends MongoRepository<ShoppingList, String> {

}
