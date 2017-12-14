package com.cassiomolin.example.product.service;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * Messaging channel definition.
 *
 * @author cassiomolin
 */
public interface ProductDeletedOutput {

    String PRODUCT_DELETED_OUTPUT = "productDeletedOutput";

    @Output(PRODUCT_DELETED_OUTPUT)
    MessageChannel productDeletedOutput();
}