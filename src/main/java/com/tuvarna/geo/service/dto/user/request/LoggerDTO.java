package com.tuvarna.geo.service.dto.user.request;

import java.io.Serializable;
import java.security.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoggerDTO implements Serializable {
    private String username;
    private String event;
    private String ip;
    private String timestamp;
}
