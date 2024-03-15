package com.tuvarna.geo.service;

import com.tuvarna.geo.entity.User;
import com.tuvarna.geo.service.dto.UserDTO;

public interface UserService {

    User registerUser(UserDTO user);
}
