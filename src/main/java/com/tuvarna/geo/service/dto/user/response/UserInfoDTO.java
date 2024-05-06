package com.tuvarna.geo.service.dto.user.response;

import com.tuvarna.geo.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfoDTO {
    private String username;
    private String email;
    private Boolean isblocked;
    private String userType;

    public UserInfoDTO(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.isblocked = user.getIsBlocked();
        this.userType = user.getUserType().getType();
    }

}
