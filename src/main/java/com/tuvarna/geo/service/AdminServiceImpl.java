package com.tuvarna.geo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuvarna.geo.entity.User;
import com.tuvarna.geo.entity.UserType;
import com.tuvarna.geo.mapper.UserMapper;
import com.tuvarna.geo.repository.UserRepository;
import com.tuvarna.geo.repository.UserTypeRepository;
import com.tuvarna.geo.service.dto.RestApiResponse;
import com.tuvarna.geo.service.dto.user.request.LoggerDTO;
import com.tuvarna.geo.service.dto.user.response.UserInfoDTO;
import com.tuvarna.geo.service.storage.S3Service;
import com.tuvarna.geo.service.validate.UserValidateService;

import jakarta.transaction.Transactional;

@Service
public class AdminServiceImpl implements AdminService {

    private UserRepository userRepository;
    private UserTypeRepository userTypeRepository;
    private UserValidateService userValidateService;
    private S3Service s3Service;

    @Autowired
    public AdminServiceImpl(UserRepository userRepository, UserTypeRepository userTypeRepository,
            UserValidateService userValidateService,
            S3Service s3Service) {
        this.userRepository = userRepository;
        this.userTypeRepository = userTypeRepository;
        this.userValidateService = userValidateService;
        this.s3Service = s3Service;
    }

    @Override
    @Transactional
    @SuppressWarnings({ "squid:S3457", "squid:S2629" })
    public RestApiResponse<List<LoggerDTO>> getLogs(String userType) {
        userValidateService.validateUseTypeExistWithoutSuperAdmin(userType);
        List<LoggerDTO> logs = s3Service.readLogs(userType);

        return new RestApiResponse<>(logs, "Logs retrieved!", 201);
    }

    @Override
    @Transactional
    @SuppressWarnings({ "squid:S3457", "squid:S2629" })
    public RestApiResponse<Void> saveLog(LoggerDTO loggerDTO, String userType) {
        userValidateService.validateUseTypeExistWithoutSuperAdmin(userType);
        s3Service.store(loggerDTO, userType);

        return new RestApiResponse<>(null, "Log saved!", 201);
    }

    @Override
    public RestApiResponse<Void> block(String email, Boolean blocked) {

        userRepository.updateIsBlockedByEmail(email, blocked);
        return new RestApiResponse<>(null,
                "User " + email + (Boolean.TRUE.equals(blocked) ? " blocked!" : " unblocked!"), 201);
    }

    @Override
    public RestApiResponse<Void> promote(String email, String userType) {

        userValidateService.validateUseTypeExistWithoutSuperAdmin(userType);

        User user = userRepository.findByEmail(email);
        UserType userTypeToPromote = userTypeRepository.findByType(userType);

        userValidateService.validateEqualUserTypes(user.getUserType().getType(), userType);
        userRepository.updateTypeByUserType(email, userTypeToPromote);

        return new RestApiResponse<>(null,
                "User " + email + (Boolean.TRUE.equals(userType.equals("admin")) ? " promoted!" : " demoted!"), 201);
    }

    @Override
    public RestApiResponse<List<UserInfoDTO>> getUsers(String userType) {

        userValidateService.validateUseTypeExistWithoutSuperAdmin(userType);
        List<UserInfoDTO> users = UserMapper.toListOfUserInfoDTO(userRepository.findByUsertypeType(userType));

        return new RestApiResponse<>(users,
                "Users retrieved!", 201);
    }
}
