package com.cassiomolin.example.product.service;

import com.cassiomolin.example.product.model.Product;
import com.cassiomolin.example.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service that provides the a greeting.
 *
 * @author cassiomolin
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public String createProduct(Product product) {
        product = productRepository.save(product);
        return product.getId();
    }

    public List<Product> findProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> findProduct(String id) {
        return Optional.ofNullable(productRepository.findOne(id));
    }
}