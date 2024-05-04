package com.tuvarna.geo.service.dto.user.request;

import java.net.InetAddress;
import java.security.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoggerQueryDTO {
    private String username;
    private String event;
    private InetAddress ip;
    private Timestamp timestamp;
}
