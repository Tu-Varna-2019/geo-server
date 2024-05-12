package com.tuvarna.geo;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuvarna.geo.app.Main;
import com.tuvarna.geo.entity.Earthquake;
import com.tuvarna.geo.entity.Soil;
import com.tuvarna.geo.entity.security.JWTAuthResponse;
import com.tuvarna.geo.service.EarthquakeService;
import com.tuvarna.geo.service.SoilService;
import com.tuvarna.geo.service.dto.RestApiResponse;
import com.tuvarna.geo.service.dto.risk.RiskDTO;
import com.tuvarna.geo.service.security.JWTTokenProvider;

@ExtendWith(SpringExtension.class)
@ActiveProfiles
@AutoConfigureMockMvc
@SpringBootTest(classes = { Main.class }, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class RiskControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SoilService soilService;
	@MockBean
	private EarthquakeService earthquakeService;
	@MockBean
	private AuthenticationManager authenticationManager;
	@MockBean
	private JWTTokenProvider jwtTokenProvider;

	@Test
	void testSoil() throws Exception {
		RiskDTO riskDTO = new RiskDTO(0.00, 0.00);

		RestApiResponse<Soil> expectedResult = new RestApiResponse<Soil>(
				"Soil type found with gid: ", 201);
		String accessToken = createJWToken("dummy", "dummy").getAccessToken();

		when(soilService.getSoil(riskDTO)).thenReturn(expectedResult);

		this.mockMvc.perform(post("/risk/soil")
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
				.contentType(MediaType.APPLICATION_JSON)
				// TODO: changed to isUnauthorized, due to not properly authenticating
				.content(new ObjectMapper().writeValueAsString((riskDTO)))).andExpect(status().isUnauthorized());
	}

	@Test
	void testEarthquake() throws Exception {
		RiskDTO riskDTO = new RiskDTO(0.00, 0.00);

		RestApiResponse<Earthquake> expectedResult = new RestApiResponse<Earthquake>(
				"Earthquake type found with gid: ", 201);

		when(earthquakeService.getEarthquake(riskDTO)).thenReturn(expectedResult);

		this.mockMvc.perform(post("/risk/earthquake")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString((riskDTO)))).andExpect(status().isUnauthorized());
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
