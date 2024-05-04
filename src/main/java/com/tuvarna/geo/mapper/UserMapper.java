package com.tuvarna.geo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.tuvarna.geo.entity.User;
import com.tuvarna.geo.service.dto.user.request.LoginUserDTO;
import com.tuvarna.geo.service.dto.user.request.RegisterUserDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper mapperInstance = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "password", ignore = true)
    User toEntity(RegisterUserDTO userDto);

    @Mapping(target = "email", source = "userDto.email")
    @Mapping(target = "password", source = "userDto.password")
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
}
