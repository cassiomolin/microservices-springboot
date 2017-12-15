package com.cassiomolin.example.shoppinglist.api.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * API model for creating a shopping list.
 *
 * @author cassiomolin
 */
public class CreateOrUpdateShoppingListPayload {

    @NotNull
    @NotBlank
    private String name;

    @Valid
    private List<Item> items = new ArrayList<>();

    public String getName() {
        return name;
    }

    public CreateOrUpdateShoppingListPayload setName(String name) {
        this.name = name;
        return this;
    }

    public List<Item> getItems() {
        return items;
    }

    public CreateOrUpdateShoppingListPayload setItems(List<Item> items) {
        this.items = items;
        return this;
    }

    public static class Item {

        @NotNull
        private String id;

        public String getId() {
            return id;
        }

        public Item setId(String id) {
            this.id = id;
            return this;
        }
    }
}
