package com.cassiomolin.example.commons.api.model;

/**
 * API domain to indicate an error.
 *
 * @author cassiomolin
 */
public class ApiError {

    private Long timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    public Long getTimestamp() {
        return timestamp;
    }

    public ApiError setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public ApiError setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getError() {
        return error;
    }

    public ApiError setError(String error) {
        this.error = error;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ApiError setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getPath() {
        return path;
    }

    public ApiError setPath(String path) {
        this.path = path;
        return this;
    }
}
