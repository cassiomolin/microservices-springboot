package com.cassiomolin.example.gateway;

import com.cassiomolin.example.gateway.filter.CustomLocationRewriteFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public CustomLocationRewriteFilter locationRewriteFilter() {
        return new CustomLocationRewriteFilter();
    }
}