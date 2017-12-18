package com.cassiomolin.example.shoppinglist.config;

import com.google.common.cache.CacheBuilder;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * Cache configuration.
 *
 * @author cassiomolin
 */
@Configuration
@EnableCaching
public class CachingConfiguration extends CachingConfigurerSupport {

    public static final String PRODUCTS_CACHE = "products";

    @Bean
    @Override
    public CacheManager cacheManager() {

        return new ConcurrentMapCacheManager() {

            @Override
            protected Cache createConcurrentMapCache(final String name) {
                return new ConcurrentMapCache(name, CacheBuilder.newBuilder()
                        .expireAfterWrite(30, TimeUnit.MINUTES)
                        .maximumSize(100).build().asMap(), false);
            }
        };
    }
}