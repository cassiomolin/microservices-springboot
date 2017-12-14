package com.cassiomolin.example.commons.api.provider;

import com.cassiomolin.example.commons.api.model.ApiError;
import com.fasterxml.jackson.core.JsonParseException;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.time.Instant;

/**
 * Component that maps a {@link JsonParseException} to a HTTP response.
 *
 * @author cassiomolin
 */
@Provider
public class JsonParseExceptionMapper implements ExceptionMapper<JsonParseException> {

    @Context
    private UriInfo uriInfo;

    @Override
    public Response toResponse(JsonParseException exception) {

        ApiError error = new ApiError();
        error.setTimestamp(Instant.now().toEpochMilli());
        error.setStatus(Response.Status.BAD_REQUEST.getStatusCode());
        error.setError(Response.Status.BAD_REQUEST.getReasonPhrase());
        error.setMessage("Request JSON cannot be parsed");
        error.setPath(uriInfo.getAbsolutePath().getPath());

        return Response.status(error.getStatus()).entity(error).type(MediaType.APPLICATION_JSON).build();
    }
}