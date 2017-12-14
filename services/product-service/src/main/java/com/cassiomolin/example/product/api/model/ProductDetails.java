package com.cassiomolin.example.product.api.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Null;

public class ProductDetails {

    @Null
    private String id;

    @NotBlank
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
