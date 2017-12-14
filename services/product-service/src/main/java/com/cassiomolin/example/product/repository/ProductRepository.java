package com.cassiomolin.example.product.repository;

import com.cassiomolin.example.product.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * MongoDB repository for products.
 *
 * @author cassiomolin
 */
public interface ProductRepository extends MongoRepository<Product, String> {

}
