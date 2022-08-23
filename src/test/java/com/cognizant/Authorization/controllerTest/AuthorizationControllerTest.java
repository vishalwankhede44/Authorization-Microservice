package com.cognizant.Authorization.controllerTest;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import com.cognizant.Authorization.controller.JwtController;
import com.cognizant.Authorization.model.JwtRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class AuthorizationControllerTest {
	
	@Autowired
	private JwtController jwtController;
	
	@Autowired
	private MockMvc mockMvc;
	

	@Test
	public void contextLoads() {
		assertNotNull(jwtController);
	}
	
	@Test
	public void loginTestSuccess() throws Exception {
		JwtRequest admin = new JwtRequest("Vishal", "Vishal123");

		ResultActions actions = mockMvc
				.perform(post("/token").contentType(MediaType.APPLICATION_JSON).content(asJsonString(admin)));
		actions.andExpect(status().isOk());
	}

	@Test
	public void loginTestFail() throws Exception {
		JwtRequest admin = new JwtRequest("WrongUser", "password1");

		ResultActions actions = mockMvc
				.perform(post("/token").contentType(MediaType.APPLICATION_JSON).content(asJsonString(admin)));
		actions.andExpect(status().isNotFound());
	}

	public static String asJsonString(JwtRequest admin) throws Exception {
		
			return new ObjectMapper().writeValueAsString(admin);
		
	}
	
}
