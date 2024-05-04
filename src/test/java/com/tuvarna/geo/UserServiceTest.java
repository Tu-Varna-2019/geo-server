package com.tuvarna.geo;

import com.tuvarna.geo.entity.User;
import com.tuvarna.geo.repository.UserRepository;
import com.tuvarna.geo.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/*@SpringBootTest
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testGetUserById() {
        String userEmail = "junitTestEmail2@example.com";
        User mockUser = new User(userEmail, "John Doe", "password123!");


        when(userRepository.findByEmail(userEmail)).thenReturn(Optional.of(mockUser));

        // Call the method to test
        User result = userService.getUserByEmail(email);

        // Validate the results
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        assertEquals(email, result.getEmail());
    }
}*/
