package com.cassiomolin.example.shoppinglist.service;

import com.cassiomolin.example.commons.api.exception.UnprocessableEntityException;
import com.cassiomolin.example.shoppinglist.config.CachingConfiguration;
import com.cassiomolin.example.shoppinglist.domain.Product;
import com.cassiomolin.example.shoppinglist.domain.ShoppingList;
import com.cassiomolin.example.shoppinglist.repository.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;

/**
 * Service that provides operations for shopping lists.
 *
 * @author cassiomolin
 */
@Service
public class ShoppingListService {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private ProductApiClient productApiClient;

    @Autowired
    private ShoppingListRepository shoppingListRepository;

    public List<ShoppingList> getShoppingLists() {
        List<ShoppingList> shoppingLists = shoppingListRepository.findAll();
        shoppingLists.forEach(this::fillProductDetails);
        return shoppingLists;
    }

    public String createShoppingList(ShoppingList shoppingList) {
        validateItems(shoppingList);
        shoppingList = shoppingListRepository.save(shoppingList);
        return shoppingList.getId();
    }

    public void updateShoppingList(ShoppingList shoppingList) {
        validateItems(shoppingList);
        shoppingListRepository.save(shoppingList);
    }

    private void validateItems(ShoppingList shoppingList) {
        shoppingList.getItems().forEach(product -> {
            if (!productApiClient.checkIfProductExists(product.getId())) {
                throw new UnprocessableEntityException(String.format("Item not found with id %s", product.getId()));
            }
        });
    }

    public ShoppingList getShoppingList(String id) {
        ShoppingList shoppingList = shoppingListRepository.findOne(id);
        if (shoppingList == null) {
            throw new NotFoundException();
        } else {
            fillProductDetails(shoppingList);
            return shoppingList;
        }
    }

    public void deleteShoppingList(String id) {
        ShoppingList shoppingList = shoppingListRepository.findOne(id);
        if (shoppingList == null) {
            throw new NotFoundException();
        } else {
            shoppingListRepository.delete(id);
        }
    }

    private void fillProductDetails(ShoppingList shoppingList) {
        shoppingList.getItems().forEach(item -> {
            Optional<Product> optionalProduct = productApiClient.getProduct(item.getId());
            optionalProduct.ifPresent(product -> item.setName(product.getName()));
        });
    }

    @CacheEvict(cacheNames = CachingConfiguration.PRODUCTS_CACHE, key = "#product.id")
    @StreamListener(ProductInputChannel.PRODUCT_DELETED_INPUT)
    public void handleDeletedProduct(Product product) {
        shoppingListRepository.deleteProductsById(product.getId());
    }

    @StreamListener(ProductInputChannel.PRODUCT_UPDATED_INPUT)
    public void handleUpdatedProduct(Product product) {
        cacheManager.getCache(CachingConfiguration.PRODUCTS_CACHE).put(product.getId(), product);
    }
}