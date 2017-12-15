package com.cassiomolin.example.shoppinglist;

import com.cassiomolin.example.shoppinglist.service.ProductInputChannel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * Spring Boot application entry point.
 *
 * @author cassiomolin
 */
@SpringBootApplication
@EnableDiscoveryClient
@RibbonClient("shoppingList")
@EnableBinding({ProductInputChannel.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}