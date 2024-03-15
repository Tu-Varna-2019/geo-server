package com.tuvarna.geo.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDTO {
    private String username;
    private String email;
    private String password;
    private Boolean isblocked;
    private String usertype;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty("isblocked")
    public Boolean getIsBlocked() {
        return isblocked;
    }

    @JsonProperty("isblocked")
    public void setIsBlocked(Boolean isblocked) {
        this.isblocked = isblocked;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }
}
