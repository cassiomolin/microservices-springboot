package com.cassiomolin.example.product.service;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ProductOutput {

    String PRODUCT_OUTPUT = "productOutput";

    @Output(PRODUCT_OUTPUT)
    MessageChannel productOutput();
}