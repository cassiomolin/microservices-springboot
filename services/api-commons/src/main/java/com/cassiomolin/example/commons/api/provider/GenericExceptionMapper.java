package com.cassiomolin.example.commons.api.provider;

import com.cassiomolin.example.commons.api.exception.UnprocessableEntityException;
import com.cassiomolin.example.commons.api.model.ApiError;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.time.Instant;

/**
 * Component that maps an exception to a HTTP response.
 *
 * @author cassiomolin
 */
@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

    @Context
    private UriInfo uriInfo;

    @Override
    public Response toResponse(Throwable exception) {

        ApiError error = new ApiError();
        error.setTimestamp(Instant.now().toEpochMilli());
        error.setStatus(getStatusCode(exception));
        error.setError(getError(exception));
        error.setMessage(exception.getMessage());
        error.setPath(uriInfo.getAbsolutePath().getPath());

        return Response.status(error.getStatus()).entity(error).type(MediaType.APPLICATION_JSON).build();
    }

    private int getStatusCode(Throwable exception) {
        if (exception instanceof WebApplicationException) {
            return ((WebApplicationException) exception).getResponse().getStatus();
        } else {
            return Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
        }
    }

    private String getError(Throwable exception) {
        if (exception instanceof WebApplicationException) {
            if (exception instanceof UnprocessableEntityException) {
                return "Unprocessable Entity";
            } else {
                return ((WebApplicationException) exception).getResponse().getStatusInfo().getReasonPhrase();
            }
        } else {
            return Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
    }
}
