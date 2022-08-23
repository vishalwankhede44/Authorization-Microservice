package com.cognizant.Authorization.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.Authorization.exception.BadRequestException;
import com.cognizant.Authorization.exception.UserNotFoundException;
import com.cognizant.Authorization.model.JwtRequest;
import com.cognizant.Authorization.model.JwtResponse;
import com.cognizant.Authorization.services.CustomUserDetailsService;
import com.cognizant.Authorization.util.JwtUtil;

@RestController
@CrossOrigin
@SuppressWarnings("PMD.GuardLogStatementJavaUtil")
public class JwtController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtController.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		
		LOGGER.info("Generate Token Started");
		
		if(jwtRequest.getUsername()==null){
			LOGGER.info("Generate Token Ended with Exception - Username is required to generate token");
			throw new BadRequestException("username is required to generate token");
		}
		
		if(jwtRequest.getPassword()==null){
			LOGGER.info("Generate Token Ended with Exception - Password is required to generate token");
			throw new BadRequestException("password is required to generate token");
		}
			
		
		
		try{
			
		this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
		
		}catch(BadCredentialsException be){
			be.printStackTrace();
			LOGGER.info("Generate Token Ended with Exception - Invalid Credentials ");
			throw new UserNotFoundException("Invalid Credentials");
		}
		
		UserDetails userDetails =  this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
		String token = this.jwtUtil.generateToken(userDetails.getUsername());
		
		LOGGER.info("Generate Token Ended Successfully");
		return ResponseEntity.ok(new JwtResponse(token));
		
	}
	
	@GetMapping("/validate")
	public ResponseEntity<?> authorization(@RequestHeader("Authorization") String token) throws UserNotFoundException{
		String tokenInput = token.substring(7).trim();
		LOGGER.info("Token Validation Started");
		try{
			UserDetails user = this.customUserDetailsService.loadUserByUsername(jwtUtil.extractUsername(tokenInput));
			
			if (jwtUtil.validateToken(tokenInput, user)) {
				LOGGER.info("Token Validation Ended - Valid Token");
				return new ResponseEntity<>(true, HttpStatus.OK);
			} else {
				LOGGER.info("Token Validation Ended - Invalid Token");
				return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
			}
		}catch(UserNotFoundException unfe){
			if(LOGGER.isDebugEnabled())
				LOGGER.debug("Token Validation Ended - User Not Found Exception - {} ",unfe.getMessage());			
			throw unfe;
		}

	}
	
	
	
	
	
}
