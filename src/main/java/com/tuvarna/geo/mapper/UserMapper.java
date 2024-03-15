package com.tuvarna.geo.mapper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.tuvarna.geo.entity.User;
import com.tuvarna.geo.service.dto.UserDTO;

public class UserMapper {
    public static User toEntity(UserDTO userDto, BCryptPasswordEncoder encodePassword) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(encodePassword.encode(userDto.getPassword()));
        user.setIsBlocked(userDto.getIsBlocked());

        return user;
    }

    public static UserDTO toDto(User user) {
        UserDTO userDto = new UserDTO();
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setIsBlocked(user.getIsBlocked());

        return userDto;
    }
}
