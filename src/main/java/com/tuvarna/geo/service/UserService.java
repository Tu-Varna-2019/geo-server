package com.tuvarna.geo.service;

import com.tuvarna.geo.service.dto.RestApiResponse;
import com.tuvarna.geo.service.dto.UserDTO;

public interface UserService {

    RestApiResponse<Void> registerUser(UserDTO user);
}
