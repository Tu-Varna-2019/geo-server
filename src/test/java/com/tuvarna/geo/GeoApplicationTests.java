package com.tuvarna.geo;

import com.tuvarna.geo.app.Main;
import com.tuvarna.geo.service.UserService;
import com.tuvarna.geo.service.dto.RestApiResponse;
import com.tuvarna.geo.service.dto.user.LoginUserDTO;
import com.tuvarna.geo.service.dto.user.RegisterUserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class GeoApplicationTests {

    @MockBean
    public UserService mockUserService;

    @Autowired
    public MockMvc mockMvc;

    @Test
    public void testUnSuccessfulRegistration() throws Exception {
        RegisterUserDTO validUserDto = new RegisterUserDTO("junitTest2", "junitTestEmail2@example.com", "password123!", false, "customer");

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<RegisterUserDTO> request = new HttpEntity<>(validUserDto);
        RestApiResponse<Void> restApiResponse = restTemplate.postForObject("http://localhost:8080/register", request, RestApiResponse.class);

        assertEquals(400, restApiResponse.getStatus());

    }

    @Test
    public void testSuccessfulLogin() throws Exception {
        LoginUserDTO validUserDto = new LoginUserDTO("junitTestEmail2@example.com", "password123!");

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<LoginUserDTO> request = new HttpEntity<>(validUserDto);
        RestApiResponse<Void> restApiResponse = restTemplate.postForObject("http://localhost:8080/login", request, RestApiResponse.class);

        assertEquals(201, restApiResponse.getStatus());
    }
}

