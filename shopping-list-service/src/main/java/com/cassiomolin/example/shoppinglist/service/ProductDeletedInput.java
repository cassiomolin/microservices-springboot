package com.cassiomolin.example.shoppinglist.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface ProductDeletedInput {

    String PRODUCT_DELETED_INPUT = "productDeletedInput";

    @Input(PRODUCT_DELETED_INPUT)
    SubscribableChannel productDeletedInput();
}