package com.tuvarna.geo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String password;

    @Column(nullable = false, unique = true)
    private boolean isBlocked;

    @Column(nullable = false, unique = true)
    @JsonIgnore
    private UserType userType;

    @Column(nullable = false, unique = true)
    private String type;

    public User() {
    }

    public User(String email, String username, String password, String type) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.isBlocked = false;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public String getType() {
        return type;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setisBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
