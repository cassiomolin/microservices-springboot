package com.cassiomolin.example.gateway;

import com.cassiomolin.example.gateway.filter.CustomLocationRewriteFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * Spring Boot application entry point.
 *
 * @author cassiomolin
 */
@SpringBootApplication
@EnableZuulProxy
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CustomLocationRewriteFilter locationRewriteFilter() {
        return new CustomLocationRewriteFilter();
    }
}