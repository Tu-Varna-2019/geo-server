package com.tuvarna.geo.service;

import java.util.List;

import com.tuvarna.geo.service.dto.RestApiResponse;
import com.tuvarna.geo.service.dto.user.request.LoggerDTO;
import com.tuvarna.geo.service.dto.user.response.UserInfoDTO;

public interface AdminService {

    RestApiResponse<Void> saveLog(LoggerDTO loggerDTO, String userType);

    RestApiResponse<List<LoggerDTO>> getLogs(String userType);

    RestApiResponse<Void> block(String email, Boolean blocked);

    RestApiResponse<List<UserInfoDTO>> getUsers(String userType);

}
