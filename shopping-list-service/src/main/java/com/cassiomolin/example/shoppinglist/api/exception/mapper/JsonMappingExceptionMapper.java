package com.cassiomolin.example.shoppinglist.api.exception.mapper;

import com.fasterxml.jackson.databind.JsonMappingException;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.time.Instant;

@Provider
public class JsonMappingExceptionMapper implements ExceptionMapper<JsonMappingException> {

    @Context
    private UriInfo uriInfo;

    @Override
    public Response toResponse(JsonMappingException exception) {

        ApiError error = new ApiError();
        error.setTimestamp(Instant.now().toEpochMilli());
        error.setStatus(Response.Status.BAD_REQUEST.getStatusCode());
        error.setError(Response.Status.BAD_REQUEST.getReasonPhrase());
        error.setMessage("Request JSON cannot be parsed");
        error.setPath(uriInfo.getAbsolutePath().getPath());

        return Response.status(error.getStatus()).entity(error).type(MediaType.APPLICATION_JSON).build();
    }
}