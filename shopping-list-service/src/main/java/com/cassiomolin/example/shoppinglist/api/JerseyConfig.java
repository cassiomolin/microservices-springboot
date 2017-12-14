package com.cassiomolin.example.shoppinglist.api;

import com.cassiomolin.example.shoppinglist.api.exception.mapper.GenericExceptionMapper;
import com.cassiomolin.example.shoppinglist.api.exception.mapper.JsonMappingExceptionMapper;
import com.cassiomolin.example.shoppinglist.api.exception.mapper.JsonParseExceptionMapper;
import com.fasterxml.jackson.databind.JsonMappingException;
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