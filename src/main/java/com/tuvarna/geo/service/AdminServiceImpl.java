package com.tuvarna.geo.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuvarna.geo.service.dto.RestApiResponse;
import com.tuvarna.geo.service.dto.user.request.BlockUserDTO;
import com.tuvarna.geo.service.dto.user.request.LoggerDTO;
import com.tuvarna.geo.service.storage.S3Service;

import jakarta.transaction.Transactional;

@Service
public class AdminServiceImpl implements AdminService {
    private static final Logger logger = LogManager.getLogger(AdminServiceImpl.class.getName());

    // private AdminRepository adminRepository;
    private S3Service s3Service;

    @Autowired
    public AdminServiceImpl(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @Override
    @Transactional
    @SuppressWarnings({ "squid:S3457", "squid:S2629" })
    public RestApiResponse<List<LoggerDTO>> getLogs(LoggerDTO loggerDTO) {

        return new RestApiResponse<>(null, "Log saved!", 201);
    }

    @Override
    @Transactional
    @SuppressWarnings({ "squid:S3457", "squid:S2629" })
    public RestApiResponse<Void> saveLog(LoggerDTO loggerDTO) {
        s3Service.store(loggerDTO);

        return new RestApiResponse<>(null, "Log saved!", 201);
    }

    @Override
    public RestApiResponse<Void> block(BlockUserDTO blockUserDTO) {

        return new RestApiResponse<>(null, "User " + blockUserDTO.getEmail() + " blocked!", 201);
    }
}
