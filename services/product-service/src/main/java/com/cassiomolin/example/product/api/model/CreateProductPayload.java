package com.cassiomolin.example.product.api.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * API model with details required to create a product.
 *
 * @author cassiomolin
 */
public class CreateProductPayload {

    @NotNull
    @NotBlank
    private String name;

    public String getName() {
        return name;
    }

    public CreateProductPayload setName(String name) {
        this.name = name;
        return this;
    }
}
