package com.cognizant.Authorization.modelTest;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.meanbean.test.BeanTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.Authorization.model.JwtRequest;
import com.cognizant.Authorization.model.JwtResponse;
import com.cognizant.Authorization.model.User;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AuthorizationModelTest {

	@Test
	public void testUserBean(){
		final BeanTester beanTester = new BeanTester();
		beanTester.getFactoryCollection();
		beanTester.testBean(User.class);
	}
	
	@Test
	public void testJWTRequestBean(){
		final BeanTester beanTester = new BeanTester();
		beanTester.getFactoryCollection();
		beanTester.testBean(JwtRequest.class);
	}
	
	@Test
	public void testJWTResponsetBean(){
		final BeanTester beanTester = new BeanTester();
		beanTester.getFactoryCollection();
		beanTester.testBean(JwtResponse.class);
	}
}
