package com.cassiomolin.example.shoppinglist.api.model;

import com.cassiomolin.example.commons.api.validation.groups.Create;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;

/**
 * API model for creating a shopping list.
 *
 * @author cassiomolin
 */
public class ShoppingListDetails {

    @Null(groups = Create.class)
    private String id;

    @NotNull
    @NotBlank
    private String name;

    @Valid
    private List<ProductDetails> items = new ArrayList<>();

    public String getId() {
        return id;
    }

    public ShoppingListDetails setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ShoppingListDetails setName(String name) {
        this.name = name;
        return this;
    }

    public List<ProductDetails> getItems() {
        return items;
    }

    public ShoppingListDetails setItems(List<ProductDetails> items) {
        this.items = items;
        return this;
    }
}
