package com.cassiomolin.example.shoppinglist.api;

import com.cassiomolin.example.shoppinglist.service.ProductNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Exception mapper for {@link ProductNotFoundException}.
 *
 * @author cassiomolin
 */
@Provider
public class ProductNotFoundExceptionMapper implements ExceptionMapper<ProductNotFoundException> {

    @Override
    public Response toResponse(ProductNotFoundException exception) {
        return Response.status(422).entity(exception.getMessage()).build();
    }
}
