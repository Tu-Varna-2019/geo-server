package com.tuvarna.geo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tuvarna.geo.entity.User;
import com.tuvarna.geo.mapper.UserMapper;
import com.tuvarna.geo.repository.UserRepository;
import com.tuvarna.geo.repository.UserTypeRepository;
import com.tuvarna.geo.service.dto.UserDTO;
import com.tuvarna.geo.service.validate.UserValidateService;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

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

        // Check if the user already exists
        userValidateService.validateUserDoesNotExist(userDto.getEmail());

        // Check if the user type exists
        user.setUserType(userValidateService.validateUserTypeExists(userDto.getUsertype()));

        return userRepository.save(user);
    }
}
