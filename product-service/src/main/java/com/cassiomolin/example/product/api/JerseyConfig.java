package com.cassiomolin.example.product.api;

import com.cassiomolin.example.commons.api.GenericExceptionMapper;
import com.cassiomolin.example.commons.api.JsonMappingExceptionMapper;
import com.cassiomolin.example.commons.api.JsonParseExceptionMapper;
import com.cassiomolin.example.commons.api.ObjectMapperContextResolver;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

/**
 * Jersey configuration.
 *
 * @author cassiomolin
 */
@Component
@ApplicationPath("api")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(ProductResource.class);
        register(ObjectMapperContextResolver.class);
        register(GenericExceptionMapper.class);
        register(JsonMappingExceptionMapper.class, 1);
        register(JsonParseExceptionMapper.class, 1);
    }
}