package com.cassiomolin.example.product.service;

import com.cassiomolin.example.product.model.Product;
import com.cassiomolin.example.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    @Qualifier(ProductDeletedOutput.PRODUCT_DELETED_OUTPUT)
    private MessageChannel messageChannel;

    public String createProduct(Product product) {
        product = productRepository.save(product);
        return product.getId();
    }

    public List<Product> findProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> findProduct(String id) {
        Product one = productRepository.findOne(id);
        return Optional.ofNullable(one);
    }

    public Optional<Product> deleteProduct(String id) {
        Optional<Product> optionalProduct = Optional.ofNullable(productRepository.findOne(id));
        if (optionalProduct.isPresent()) {
            productRepository.delete(id);
            messageChannel.send(MessageBuilder.withPayload(optionalProduct.get()).build());
        }
        return optionalProduct;
    }
}