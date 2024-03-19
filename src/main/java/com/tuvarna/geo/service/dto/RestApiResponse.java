package com.tuvarna.geo.service.dto;

public class RestApiResponse<T> {
    private T data;
    private String message;
    private int status;

    public RestApiResponse(T data, String message, int status) {
        this.data = data;
        this.message = message;
        this.status = status;
    }

    public RestApiResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
