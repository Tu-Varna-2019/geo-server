package com.tuvarna.geo.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tuvarna.geo.config.security.JWTTokenProvider;
import com.tuvarna.geo.entity.User;
import com.tuvarna.geo.entity.security.JWTAuthResponse;
import com.tuvarna.geo.mapper.UserMapper;
import com.tuvarna.geo.repository.UserRepository;
import com.tuvarna.geo.repository.UserTypeRepository;
import com.tuvarna.geo.service.dto.RestApiResponse;
import com.tuvarna.geo.service.dto.user.LoggedInUserDTO;
import com.tuvarna.geo.service.dto.user.LoginUserDTO;
import com.tuvarna.geo.service.dto.user.RegisterUserDTO;
import com.tuvarna.geo.service.validate.UserValidateService;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class.getName());

    private UserRepository userRepository;
    private UserValidateService userValidateService;
    private UserMapper userMapper;
    private BCryptPasswordEncoder encodePassword;
    private AuthenticationManager authenticationManager;
    private JWTTokenProvider jwtTokenProvider;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserTypeRepository userTypeRepository,
            UserValidateService userValidateService, UserMapper userMapper, BCryptPasswordEncoder encodePassword,
            AuthenticationManager authenticationManager, JWTTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.userValidateService = userValidateService;
        this.userMapper = userMapper;
        this.encodePassword = encodePassword;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    @Transactional
    @SuppressWarnings("squid:S3457")
    public RestApiResponse<Void> registerUser(RegisterUserDTO userDto) {
        User user = userMapper.toEntity(userDto, encodePassword);
        logger.info("Mapping user DTO to entity");

        userValidateService.validateUserDoesNotExist(userDto.getEmail());
        logger.info("User does not exist with given email: " + userDto.getEmail());
        user.setUserType(userValidateService.validateUserTypeExists(userDto.getUsertype()));
        logger.info("User type exists: " + userDto.getUsertype());
        userRepository.save(user);

        logger.info("User %s registered successfully", userDto.getEmail());
        return new RestApiResponse<>(null, "User registered successfully", 201);

    }

    @Override
    @Transactional
    @SuppressWarnings({ "squid:S3457", "squid:S2629" })
    public RestApiResponse<LoggedInUserDTO> authenticateUser(LoginUserDTO userDto) {

        // Authentication authentication = authenticationManager.authenticate(
        // new UsernamePasswordAuthenticationToken(
        // userDto.getEmail(), userDto.getPassword()));

        // SecurityContext sc = SecurityContextHolder.getContext();
        // sc.setAuthentication(authentication);
        // String token = jwtTokenProvider.generateToken(authentication);
        // JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        // jwtAuthResponse.setAccessToken(token);

        User user = userMapper.toEntity(userDto);
        logger.info("Mapping user DTO to entity");
        userValidateService.validateUserExists(user.getEmail());
        logger.info("User exist with given email: " + userDto.getEmail());

        User userFromDb = userRepository.findByEmail(user.getEmail());
        userValidateService.validatePasswordMatch(encodePassword, user.getPassword(), userFromDb.getPassword());
        userValidateService.validateIsUserBlocked(userFromDb.getIsBlocked());
        logger.info("User logged in successfully. Sending credentials: %s", userFromDb.toString());

        LoggedInUserDTO loggedUserDTO = new LoggedInUserDTO(userFromDb);

        return new RestApiResponse<>(loggedUserDTO, "User logged in successfully", 201);
    }

}
