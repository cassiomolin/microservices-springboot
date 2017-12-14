package com.cassiomolin.example.shoppinglist.api.exception;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;

/**
 * Exception to indicate that an entity cannot be processed.
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
