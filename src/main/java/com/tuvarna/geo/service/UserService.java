package com.tuvarna.geo.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.tuvarna.geo.service.dto.RestApiResponse;
import com.tuvarna.geo.service.dto.user.LoggedInUserDTO;
import com.tuvarna.geo.service.dto.user.LoginUserDTO;
import com.tuvarna.geo.service.dto.user.RegisterUserDTO;

public interface UserService {

    RestApiResponse<Void> registerUser(RegisterUserDTO user);

    RestApiResponse<LoggedInUserDTO> authenticateUser(LoginUserDTO user);

}
