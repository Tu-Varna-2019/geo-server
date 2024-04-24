package com.tuvarna.geo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "\"user\"")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = false)
    private String password;

    @Column(nullable = false, unique = false)
    private boolean isblocked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usertype", nullable = false)
    private UserType usertype;

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.isblocked = false;
    }

    public boolean getIsBlocked() {
        return isblocked;
    }

    public UserType getUserType() {
        return usertype;
    }

    public void setUserType(UserType usertype) {
        this.usertype = usertype;
    }

    @Override
    public String toString() {
        return String.format("id=%s,username=%s,email=%s,password=***,isBlocked=%s,usertype=%s ", id, username, email,
                isblocked, username);
    }

}
