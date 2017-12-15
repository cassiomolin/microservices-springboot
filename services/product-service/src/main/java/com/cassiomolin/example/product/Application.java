package com.cassiomolin.example.product;

import com.cassiomolin.example.product.service.ProductOutputChannel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * Spring Boot application entry point.
 *
 * @author cassiomolin
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding({ProductOutputChannel.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}