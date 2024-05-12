package com.tuvarna.geo.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.tuvarna.geo.entity.User;
import com.tuvarna.geo.service.dto.user.request.LoginUserDTO;
import com.tuvarna.geo.service.dto.user.request.RegisterUserDTO;
import com.tuvarna.geo.service.dto.user.response.UserInfoDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper mapperInstance = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "password", ignore = true)
    User toEntity(RegisterUserDTO userDto);

    // @Mapping(target = "email", source = "email")
    // @Mapping(target = "password", source = "password")
    User toEntity(LoginUserDTO userDto);

    default User toEntity(RegisterUserDTO userDto, BCryptPasswordEncoder encoder) {
        User user = toEntity(userDto);
        user.setPassword(encoder.encode(userDto.getPassword()));
        return user;
    }

    public static RegisterUserDTO toRegisterUserDto(User user) {
        return new RegisterUserDTO(user.getUsername(), user.getEmail(), user.getPassword(), user.getIsBlocked(), null);

    }

    public static LoginUserDTO toLoginUserDto(User user) {
        return new LoginUserDTO(user.getEmail(), user.getPassword());
    }

    public static UserInfoDTO toUserInfoDTO(User user) {
        return new UserInfoDTO(user.getUsername(), user.getEmail(), user.getIsBlocked(), user.getUserType().getType());
    }

    public static List<UserInfoDTO> toListOfUserInfoDTO(List<User> users) {

        List<UserInfoDTO> userInfoDTOs = new ArrayList<>();
        users.forEach(user -> {
            userInfoDTOs.add(toUserInfoDTO(user));
        });

        return userInfoDTOs;
    }
}
