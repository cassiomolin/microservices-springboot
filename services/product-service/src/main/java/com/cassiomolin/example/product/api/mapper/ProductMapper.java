package com.cassiomolin.example.product.api.mapper;

import com.cassiomolin.example.product.api.model.CreateOrUpdateProductPayload;
import com.cassiomolin.example.product.api.model.QueryProductResult;
import com.cassiomolin.example.product.domain.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * Component that maps a domain model to an API model and vice-versa.
 *
 * @author cassiomolin
 */
@Mapper
public interface ProductMapper {

    Product toProduct(CreateOrUpdateProductPayload payload);

    QueryProductResult toQueryProductResult(Product product);

    void updateProduct(CreateOrUpdateProductPayload payload, @MappingTarget Product product);
}