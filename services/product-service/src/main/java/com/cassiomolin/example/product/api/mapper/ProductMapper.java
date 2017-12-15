package com.cassiomolin.example.product.api.mapper;

import com.cassiomolin.example.product.api.model.CreateProductPayload;
import com.cassiomolin.example.product.api.model.QueryProductResult;
import com.cassiomolin.example.product.domain.Product;
import org.mapstruct.Mapper;

/**
 * Component that maps a domain model to an API model and vice-versa.
 *
 * @author cassiomolin
 */
@Mapper
public interface ProductMapper {

    Product toProduct(CreateProductPayload productPayload);

    QueryProductResult toQueryProductResult(Product product);
}