package com.tuvarna.geo.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tuvarna.geo.entity.User;
import com.tuvarna.geo.entity.security.JWTAuthResponse;
import com.tuvarna.geo.mapper.UserMapper;
import com.tuvarna.geo.repository.UserRepository;
import com.tuvarna.geo.repository.UserTypeRepository;
import com.tuvarna.geo.service.dto.RestApiResponse;
import com.tuvarna.geo.service.dto.user.request.LoginUserDTO;
import com.tuvarna.geo.service.dto.user.request.RegisterUserDTO;
import com.tuvarna.geo.service.dto.user.response.LoggedInUserDTO;
import com.tuvarna.geo.service.security.JWTTokenProvider;
import com.tuvarna.geo.service.validate.UserValidateService;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class.getName());

    private UserRepository userRepository;
    private UserTypeRepository userTypeRepository;
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
        this.userTypeRepository = userTypeRepository;
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

        userValidateService.validateUserTypeExists(userDto.getUsertype());
        user.setUserType(userTypeRepository.findByType(userDto.getUsertype()));
        logger.info("User type exists: " + userDto.getUsertype());
        userRepository.save(user);

        logger.info("User %s registered successfully", userDto.getEmail());
        return new RestApiResponse<>(null, "User registered successfully", 201);

    }

    @Override
    public RestApiResponse<LoggedInUserDTO> login(LoginUserDTO userDto) {
        User user = userMapper.toEntity(userDto);
        logger.info("Mapping user DTO to entity");
        userValidateService.validateUserExists(user.getEmail());
        logger.info("User exist with given email: {}", userDto.getEmail());

        User userFromDb = userRepository.findByEmail(user.getEmail());
        userValidateService.validatePasswordMatch(encodePassword, user.getPassword(), userFromDb.getPassword());
        userValidateService.validateIsUserBlocked(userFromDb.getIsBlocked());
        logger.info("User logged in successfully. Sending credentials: %s", userFromDb.toString());

        LoggedInUserDTO loggedUserDTO = new LoggedInUserDTO(userFromDb);
        JWTAuthResponse jwtAuthResponse = createJWToken(userDto.getEmail(), userDto.getPassword());
        logger.info("jwt token: {}", jwtAuthResponse.getAccessToken());
        loggedUserDTO.setAccessToken(jwtAuthResponse.getAccessToken());

        return new RestApiResponse<>(loggedUserDTO, "User logged in successfully", 201);
    }

    private JWTAuthResponse createJWToken(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email, password));

        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        JWTAuthResponse jwt = new JWTAuthResponse();
        jwt.setAccessToken(token);
        return jwt;

    }
}
