package com.tuvarna.geo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuvarna.geo.repository.UserRepository;
import com.tuvarna.geo.service.dto.RestApiResponse;
import com.tuvarna.geo.service.dto.user.request.LoggerDTO;
import com.tuvarna.geo.service.storage.S3Service;

import jakarta.transaction.Transactional;

@Service
public class AdminServiceImpl implements AdminService {

    private UserRepository userRepository;
    private S3Service s3Service;

    @Autowired
    public AdminServiceImpl(UserRepository userRepository, S3Service s3Service) {
        this.userRepository = userRepository;
        this.s3Service = s3Service;
    }

    @Override
    @Transactional
    @SuppressWarnings({ "squid:S3457", "squid:S2629" })
    public RestApiResponse<List<LoggerDTO>> getLogs() {
        List<LoggerDTO> logs = s3Service.readLogs();

        return new RestApiResponse<>(logs, "Log saved!", 201);
    }

    @Override
    @Transactional
    @SuppressWarnings({ "squid:S3457", "squid:S2629" })
    public RestApiResponse<Void> saveLog(LoggerDTO loggerDTO) {
        s3Service.store(loggerDTO);

        return new RestApiResponse<>(null, "Log saved!", 201);
    }

    @Override
    public RestApiResponse<Void> block(String email, Boolean blocked) {

        userRepository.updateIsBlockedByEmail(email, blocked);
        return new RestApiResponse<>(null,
                "User " + email + (Boolean.TRUE.equals(blocked) ? " blocked!" : " unblocked!"), 201);
    }
}
