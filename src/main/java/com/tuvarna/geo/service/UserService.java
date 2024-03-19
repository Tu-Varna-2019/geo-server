package com.tuvarna.geo.service;

import com.tuvarna.geo.service.dto.RestApiResponse;
import com.tuvarna.geo.service.dto.user.LoginUserDTO;
import com.tuvarna.geo.service.dto.user.RegisterUserDTO;

public interface UserService {

    RestApiResponse<Void> registerUser(RegisterUserDTO user);

    RestApiResponse<Void> authenticateUser(LoginUserDTO user);
}
