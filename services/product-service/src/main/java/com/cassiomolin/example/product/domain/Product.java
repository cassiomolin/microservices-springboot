package com.cassiomolin.example.product.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Domain class that represents a product.
 *
 * @author cassiomolin
 */
@Document
public class Product {

    @Id
    private String id;

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
