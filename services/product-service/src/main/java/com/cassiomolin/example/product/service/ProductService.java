package com.cassiomolin.example.product.service;

import com.cassiomolin.example.product.domain.Product;
import com.cassiomolin.example.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import java.util.List;

/**
 * Service that provides operations for products.
 *
 * @author cassiomolin
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    @Qualifier(ProductOutputChannel.PRODUCT_DELETED_OUTPUT)
    private MessageChannel productDeletedMessageChannel;

    @Autowired
    @Qualifier(ProductOutputChannel.PRODUCT_UPDATED_OUTPUT)
    private MessageChannel productUpdatedMessageChannel;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public String createProduct(Product product) {
        product = productRepository.save(product);
        return product.getId();
    }

    public void updateProduct(Product product) {
        product = productRepository.save(product);
        productUpdatedMessageChannel.send(MessageBuilder.withPayload(product).build());
    }

    public Product getProduct(String id) {
        Product product = productRepository.findOne(id);
        if (product == null) {
            throw new NotFoundException();
        }
        return product;
    }

    public void deleteProduct(String id) {
        Product product = productRepository.findOne(id);
        if (product == null) {
            throw new NotFoundException();
        } else {
            productRepository.delete(id);
            productDeletedMessageChannel.send(MessageBuilder.withPayload(product).build());
        }
    }
}