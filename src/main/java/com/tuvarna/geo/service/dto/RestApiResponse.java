package com.tuvarna.geo.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestApiResponse<T> {
    private T data;
    private String message;
    private int status;

    public RestApiResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }

}
