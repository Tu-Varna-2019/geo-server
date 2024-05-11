package com.tuvarna.geo;

import com.tuvarna.geo.app.Main;
import com.tuvarna.geo.entity.User;
import com.tuvarna.geo.entity.UserType;
import com.tuvarna.geo.mapper.UserMapper;
import com.tuvarna.geo.repository.UserRepository;
import com.tuvarna.geo.repository.UserTypeRepository;
import com.tuvarna.geo.service.UserServiceImpl;
import com.tuvarna.geo.service.dto.RestApiResponse;
import com.tuvarna.geo.service.dto.user.request.RegisterUserDTO;
import com.tuvarna.geo.service.security.JWTTokenProvider;
import com.tuvarna.geo.service.validate.UserValidateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class UserServiceImplTest {

    private UserRepository userRepository;
    private UserTypeRepository userTypeRepository;
    private UserValidateService userValidateService;
    private UserMapper userMapper;
    private BCryptPasswordEncoder encoder;
    private UserServiceImpl userService;
    AuthenticationManager authenticationManager;
    JWTTokenProvider jwtTokenProvider;

    @BeforeEach
    void setup() {
        userRepository = Mockito.mock(UserRepository.class);
        userTypeRepository = Mockito.mock(UserTypeRepository.class);
        userValidateService = Mockito.mock(UserValidateService.class);
        userMapper = Mockito.mock(UserMapper.class);
        encoder = Mockito.mock(BCryptPasswordEncoder.class);
        authenticationManager = Mockito.mock(AuthenticationManager.class);
        jwtTokenProvider = Mockito.mock(JWTTokenProvider.class);

        userService = new UserServiceImpl(userRepository, userTypeRepository, userValidateService, userMapper, encoder,
                authenticationManager, jwtTokenProvider);
    }

    @Test
    void registerUserTest() {
        RegisterUserDTO userDto = new RegisterUserDTO("dummy", "dummy", "123",
                false, "customer");
        User user = new User();

        when(userMapper.toEntity(any(RegisterUserDTO.class), eq(encoder))).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userTypeRepository.findByType(userDto.getUsertype())).thenReturn(new UserType());

        RestApiResponse<Void> registerResponse = userService.registerUser(userDto);

        assertNotNull(registerResponse);
        assertEquals(201, registerResponse.getStatus());
        verify(userRepository).save(any(User.class));
    }
}