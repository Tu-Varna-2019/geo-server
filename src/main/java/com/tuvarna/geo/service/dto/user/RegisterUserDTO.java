package com.tuvarna.geo.service.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterUserDTO {
    private String username;
    private String email;
    private String password;
    private Boolean isblocked;
    private String usertype;

}
