package com.cassiomolin.example.shoppinglist.api.model;

import com.cassiomolin.example.commons.api.validation.groups.Create;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * API model that represents an item of a shopping list.
 *
 * @author cassiomolin
 */
public class ProductDetails {

    @NotNull
    private String id;

    @Null(groups = Create.class)
    private String name;

    public String getId() {
        return id;
    }

    public ProductDetails setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductDetails setName(String name) {
        this.name = name;
        return this;
    }
}