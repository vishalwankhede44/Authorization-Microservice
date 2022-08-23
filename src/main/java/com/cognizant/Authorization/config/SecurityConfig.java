package com.cognizant.Authorization.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cognizant.Authorization.services.CustomUserDetailsService;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService; 
	
	@Autowired
	private JwtAuthenticationFilter jwtFilter;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		LOGGER.info("SecurityConfig - configure - HttpSecurity Started");
		http
			.csrf()
			.disable()
			.cors()
			.disable()
			.authorizeRequests()
			.antMatchers("/token","/h2-console/**","/error","/v2/api-docs","/").permitAll()
			.anyRequest().authenticated()
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		http.headers().frameOptions().disable();
		
		LOGGER.info("SecurityConfig - configure - HttpSecurityy Ended");
	}
	
	//over
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		LOGGER.info("SecurityConfig - configure - AuthenticationManagerBuilder - Started");
		auth.userDetailsService(customUserDetailsService);
		LOGGER.info("SecurityConfig - configure - AuthenticationManagerBuilder - Ended");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		LOGGER.info("SecurityConfig - passwordEncoder - Started");
		LOGGER.info("SecurityConfig - passwordEncoder - Ended");
		return NoOpPasswordEncoder.getInstance();
		
	}
	
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception{
		LOGGER.info("SecurityConfig - authenticationManagerBean - Started");
		LOGGER.info("SecurityConfig - authenticationManagerBean - Ended");
		return super.authenticationManagerBean();
	}
}
