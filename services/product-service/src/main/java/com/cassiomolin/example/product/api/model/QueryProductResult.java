package com.cassiomolin.example.product.api.model;

/**
 * API model with details of a product query result.
 *
 * @author cassiomolin
 */
public class QueryProductResult {

    private String id;

    private String name;

    public String getId() {
        return id;
    }

    public QueryProductResult setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public QueryProductResult setName(String name) {
        this.name = name;
        return this;
    }
}
