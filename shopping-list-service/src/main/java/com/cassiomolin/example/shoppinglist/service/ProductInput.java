package com.cassiomolin.example.shoppinglist.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface ProductInput {

    String PRODUCT_INPUT = "productInput";

    @Input(PRODUCT_INPUT)
    SubscribableChannel productInput();
}