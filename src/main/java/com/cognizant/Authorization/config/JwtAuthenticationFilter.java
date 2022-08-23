package com.cognizant.Authorization.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cognizant.Authorization.controller.JwtController;
import com.cognizant.Authorization.exception.UserNotFoundException;
import com.cognizant.Authorization.services.CustomUserDetailsService;
import com.cognizant.Authorization.util.JwtUtil;

@Component
@SuppressWarnings("PMD.GuardLogStatementJavaUtil")
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// get jwt
		// bearer
		// validate
		LOGGER.info("JWT Authentication Filter Started ");
		String requestTokenHeader = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;
		
		if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer ")){
			jwtToken = requestTokenHeader.substring(7).trim();
			
			try{
				 LOGGER.info("JWT Authentication Filter- Try Block Started");
				username = this.jwtUtil.extractUsername(jwtToken);
				
				UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);
				
				if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
					
				   UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
				   
				   usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				   
				   SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				   
				   
				}else{
					throw new UserNotFoundException("Invalid Credentials");
				}
				LOGGER.info("JWT Authentication Filter- Try Block Ended");
			}catch(Exception e){
				if(LOGGER.isDebugEnabled())
					LOGGER.debug("JWT Authentication Filter Ended - doFilterInterval - Exception {}",e.getMessage());
			}
			
			
		}
		filterChain.doFilter(request, response);
		 LOGGER.info("JWT Authentication Filter Ended - Successully");
	}

	
}
