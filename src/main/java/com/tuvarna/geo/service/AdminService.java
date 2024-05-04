package com.tuvarna.geo.service;

import java.util.List;

import com.tuvarna.geo.service.dto.RestApiResponse;
import com.tuvarna.geo.service.dto.user.request.BlockUserDTO;
import com.tuvarna.geo.service.dto.user.request.LoggerDTO;

public interface AdminService {

    RestApiResponse<Void> saveLog(LoggerDTO loggerDTO);

    RestApiResponse<List<LoggerDTO>> getLogs(LoggerDTO loggerDTO);

    RestApiResponse<Void> block(BlockUserDTO blockUserDTO);

}
