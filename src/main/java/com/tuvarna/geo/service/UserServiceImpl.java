package com.tuvarna.geo.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tuvarna.geo.entity.User;
import com.tuvarna.geo.mapper.UserMapper;
import com.tuvarna.geo.repository.UserRepository;
import com.tuvarna.geo.repository.UserTypeRepository;
import com.tuvarna.geo.service.dto.RestApiResponse;
import com.tuvarna.geo.service.dto.UserDTO;
import com.tuvarna.geo.service.validate.UserValidateService;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class.getName());

    private UserRepository userRepository;
    private UserTypeRepository userTypeRepository;
    private UserValidateService userValidateService;

    @Autowired
    private BCryptPasswordEncoder encodePassword;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserTypeRepository userTypeRepository,
            UserValidateService userValidateService) {
        this.userRepository = userRepository;
        this.userTypeRepository = userTypeRepository;
        this.userValidateService = userValidateService;
    }

    @Override
    @Transactional
    public RestApiResponse<Void> registerUser(UserDTO userDto) {

        User user = UserMapper.toEntity(userDto, encodePassword);
        logger.info("Mapping user DTO to entity");

        // Check if the user already exists
        userValidateService.validateUserDoesNotExist(userDto.getEmail());
        logger.info("User %s does not exist", userDto.getEmail());

        // Check if the user type exists
        user.setUserType(userValidateService.validateUserTypeExists(userDto.getUsertype()));
        logger.info("User type %s exists", userDto.getUsertype());

        userRepository.save(user);

        logger.info("User %s registered successfully", userDto.getEmail());
        return new RestApiResponse<>(null, "User registered successfully", 201);

    }

    @Override
    @Transactional
    public RestApiResponse<Void> authenticateUser(UserDTO userDto) {

        User user = UserMapper.toEntity(userDto, encodePassword);
        logger.info("Mapping user DTO to entity");

        // Fetch the user from the database
        userValidateService.validateUserExists(user.getEmail());
        logger.info("User %s does not exist", userDto.getEmail());

        User userFromDb = userRepository.findByEmail(user.getEmail());

        // Compare the passwords
        userValidateService.validatePasswordMatch(encodePassword, user.getPassword(), userFromDb.getPassword());

        logger.info("User logged in successfully");
        return new RestApiResponse<>(null, "User logged in successfully", 201);

    }
}
