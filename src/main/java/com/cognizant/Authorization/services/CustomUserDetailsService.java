package com.cognizant.Authorization.services;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.cognizant.Authorization.exception.UserNotFoundException;
import com.cognizant.Authorization.model.User;
import com.cognizant.Authorization.repository.UserRepository;

@Service
@SuppressWarnings("PMD.GuardLogStatementJavaUtil")
public class CustomUserDetailsService implements UserDetailsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomUserDetailsService.class);

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		LOGGER.info("CustomUserDetailsService - loadUserByUsername - Started");
		User user = userRepository.findByUsername(username);
		
		try{
			LOGGER.info("CustomUserDetailsService - loadUserByUsername - User = {} - Ended Successfully",user);
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
		}catch(Exception e){
			if(LOGGER.isDebugEnabled())
			LOGGER.debug("CustomUserDetailsService - loadUserByUsername - User = {} - Ended with Exception {}",user,e.getMessage());
			throw new UserNotFoundException("Invalid Token");
		}
		
		
		
	}

}
