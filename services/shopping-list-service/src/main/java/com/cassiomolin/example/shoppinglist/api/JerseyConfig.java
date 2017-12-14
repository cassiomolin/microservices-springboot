package com.cassiomolin.example.shoppinglist.api;

import com.cassiomolin.example.commons.api.provider.GenericExceptionMapper;
import com.cassiomolin.example.commons.api.provider.JsonMappingExceptionMapper;
import com.cassiomolin.example.commons.api.provider.JsonParseExceptionMapper;
import com.cassiomolin.example.commons.api.provider.ObjectMapperContextResolver;
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
        register(ShoppingListResource.class);
        register(ObjectMapperContextResolver.class);
        register(GenericExceptionMapper.class);
        register(JsonMappingExceptionMapper.class, 1);
        register(JsonParseExceptionMapper.class, 1);
    }
}