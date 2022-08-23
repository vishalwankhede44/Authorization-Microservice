package com.cognizant.Authorization.exception;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cognizant.Authorization.controller.JwtController;



@ControllerAdvice
@RestController
public class ControllerAdviceClass extends ResponseEntityExceptionHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAdviceClass.class);
	
	@ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleUserNotFoundException(Exception ex,WebRequest webRequest){
		LOGGER.info("ControllerAdviceClass - handleUserNotFoundException - Started");
		ExceptionResponse exceptionResponse = 
				new ExceptionResponse("Invalid Credentials",new Date());
		
//		return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND);
		LOGGER.info("ControllerAdviceClass - handleUserNotFoundException - Ended");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);		
	}	
	
	@ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<ExceptionResponse> handleBadRequestException(Exception ex,WebRequest webRequest){
		LOGGER.info("ControllerAdviceClass - handleBadRequestException - Started");
		ExceptionResponse exceptionResponse = 
				new ExceptionResponse(ex.getMessage(),new Date());
		
//		return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND);
		LOGGER.info("ControllerAdviceClass - handleBadRequestException - Ended");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);		
	}	
	
}
