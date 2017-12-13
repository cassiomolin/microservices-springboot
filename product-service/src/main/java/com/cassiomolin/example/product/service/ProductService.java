package com.cassiomolin.example.product.service;

import com.cassiomolin.example.product.model.Product;
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
    @Qualifier(ProductDeletedOutput.PRODUCT_DELETED_OUTPUT)
    private MessageChannel messageChannel;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public String createProduct(Product product) {
        product = productRepository.save(product);
        return product.getId();
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
            messageChannel.send(MessageBuilder.withPayload(product).build());
        }
    }
}