package com.cassiomolin.example.shoppinglist.api.model;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * API model with details of a shopping list query result.
 *
 * @author cassiomolin
 */
public class QueryShoppingListResult {

    private String id;

    private String name;

    private ZonedDateTime createdDate;

    private List<Item> items = new ArrayList<>();

    public String getId() {
        return id;
    }

    public QueryShoppingListResult setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public QueryShoppingListResult setName(String name) {
        this.name = name;
        return this;
    }

    public List<Item> getItems() {
        return items;
    }

    public QueryShoppingListResult setItems(List<Item> items) {
        this.items = items;
        return this;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public QueryShoppingListResult setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public static class Item {

        private String id;

        private String name;

        public String getId() {
            return id;
        }

        public Item setId(String id) {
            this.id = id;
            return this;
        }

        public String getName() {
            return name;
        }

        public Item setName(String name) {
            this.name = name;
            return this;
        }
    }
}
