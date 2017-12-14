package com.cassiomolin.example.shoppinglist.repository;

import com.cassiomolin.example.shoppinglist.domain.ShoppingList;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * MongoDB repository for shopping lists.
 *
 * @author cassiomolin
 */
public interface ShoppingListRepository extends MongoRepository<ShoppingList, String>, ShoppingListRepositoryCustom {

}
