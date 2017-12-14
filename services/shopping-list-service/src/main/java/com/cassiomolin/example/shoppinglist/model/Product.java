package com.cassiomolin.example.shoppinglist.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Model class that represents a product.
 *
 * @author cassiomolin
 */
@Document
public class Product {

    @Id
    private String id;

    @Transient
    private String name;

    public String getId() {
        return id;
    }

    public Product setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }
}
