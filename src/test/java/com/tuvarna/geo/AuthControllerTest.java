package com.tuvarna.geo;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuvarna.geo.app.Main;
import com.tuvarna.geo.service.UserService;
import com.tuvarna.geo.service.dto.RestApiResponse;
import com.tuvarna.geo.service.dto.user.request.LoginUserDTO;
import com.tuvarna.geo.service.dto.user.response.LoggedInUserDTO;

@ExtendWith(SpringExtension.class)
@ActiveProfiles
@AutoConfigureMockMvc
@SpringBootTest(classes = { Main.class }, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class AuthControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@Test
	void testLogin() throws Exception {
		LoginUserDTO userDto = new LoginUserDTO("dummy", "dummy");
		RestApiResponse<LoggedInUserDTO> expectedResult = new RestApiResponse<LoggedInUserDTO>(
				"User logged in successfully", 201);

		when(userService.login(userDto)).thenReturn(expectedResult);

		this.mockMvc.perform(post("/auth/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString((userDto)))).andExpect(status().isOk())
				.andExpect(content().string(containsString("User logged in successfully")));

	}

}
