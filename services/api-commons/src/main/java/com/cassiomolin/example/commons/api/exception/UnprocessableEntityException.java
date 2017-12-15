package com.cassiomolin.example.commons.api.exception;

import javax.ws.rs.ClientErrorException;

/**
 * Exception to indicate that an entity cannot be processed. Produces a response with the {@code 422} status code (Unprocessable Entity).
 *
 * @author cassiomolin
 */
public class UnprocessableEntityException extends ClientErrorException {

    private static final int STATUS = 422;

    public UnprocessableEntityException() {
        super(STATUS);
    }

    public UnprocessableEntityException(String message) {
        super(message, STATUS);
    }

    public UnprocessableEntityException(Throwable cause) {
        super(STATUS, cause);
    }

    public UnprocessableEntityException(String message, Throwable cause) {
        super(message, STATUS, cause);
    }
}
