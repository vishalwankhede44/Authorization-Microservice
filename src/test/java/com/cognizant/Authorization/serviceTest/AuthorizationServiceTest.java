package com.cognizant.Authorization.serviceTest;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.Authorization.model.User;
import com.cognizant.Authorization.repository.UserRepository;
import com.cognizant.Authorization.services.CustomUserDetailsService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AuthorizationServiceTest {
	
	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private CustomUserDetailsService service;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	public void loadUserByUserNameShouldUserNameTest() {
		when(userRepository.findByUsername("Vishal")).thenReturn(new User(1,"Vishal","Vishal123"));
		assertThat(service.loadUserByUsername("Vishal")).isNotNull();
		verify(userRepository, Mockito.times(1)).findByUsername("Vishal");
	}
}
