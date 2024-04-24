package com.tuvarna.geo.service.dto.user;

import com.tuvarna.geo.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoggedInUserDTO {
    private Integer id;
    private String username;
    private String userType;
    private String email;
    // private String jwt;

    public LoggedInUserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.userType = user.getUserType().getType();
        this.email = user.getEmail();

        // this.jwt = jwt;
    }

}
