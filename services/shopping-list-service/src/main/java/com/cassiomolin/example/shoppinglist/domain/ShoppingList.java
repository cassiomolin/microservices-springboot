package com.cassiomolin.example.shoppinglist.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Domain class that represents a shopping list.
 *
 * @author cassiomolin
 */
@Document
public class ShoppingList {

    @Id
    private String id;

    private String name;

    @CreatedDate
    private ZonedDateTime createdDate;

    private Set<Product> items = new HashSet<>();

    public String getId() {
        return id;
    }

    public ShoppingList setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ShoppingList setName(String name) {
        this.name = name;
        return this;
    }

    public Set<Product> getItems() {
        return items;
    }

    public ShoppingList setItems(Set<Product> items) {
        this.items = items;
        return this;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public ShoppingList setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingList that = (ShoppingList) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
