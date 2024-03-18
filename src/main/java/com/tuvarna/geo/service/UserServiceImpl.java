package com.tuvarna.geo.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tuvarna.geo.controller.RegisterController;
import com.tuvarna.geo.entity.User;
import com.tuvarna.geo.mapper.UserMapper;
import com.tuvarna.geo.repository.UserRepository;
import com.tuvarna.geo.repository.UserTypeRepository;
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
    public User registerUser(UserDTO userDto) {
        User user = UserMapper.toEntity(userDto, encodePassword);
        logger.info("Mapping user DTO to entity");

        // Check if the user already exists
        userValidateService.validateUserDoesNotExist(userDto.getEmail());
        logger.info("Validated user does not exist");

        // Check if the user type exists
        user.setUserType(userValidateService.validateUserTypeExists(userDto.getUsertype()));
        logger.info("Validated user type exists");

        return userRepository.save(user);
    }
}
