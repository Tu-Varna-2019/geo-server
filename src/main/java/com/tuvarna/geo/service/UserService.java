package com.tuvarna.geo.service;

import com.tuvarna.geo.service.dto.RestApiResponse;
import com.tuvarna.geo.service.dto.user.request.LoginUserDTO;
import com.tuvarna.geo.service.dto.user.request.RegisterUserDTO;
import com.tuvarna.geo.service.dto.user.response.LoggedInUserDTO;

public interface UserService {

    RestApiResponse<Void> registerUser(RegisterUserDTO user);

    RestApiResponse<LoggedInUserDTO> login(LoginUserDTO user);

}
