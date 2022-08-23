package com.cognizant.Authorization;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.boot.test.context.SpringBootTest;

import com.cognizant.Authorization.controllerTest.AuthorizationControllerTest;
import com.cognizant.Authorization.modelTest.AuthorizationModelTest;
import com.cognizant.Authorization.serviceTest.AuthorizationServiceTest;
import com.cognizant.Authorization.services.CustomUserDetailsService;



@RunWith(Suite.class)
@SuiteClasses({AuthorizationControllerTest.class,AuthorizationModelTest.class,AuthorizationServiceTest.class})
public class AuthorizationTests {
}
