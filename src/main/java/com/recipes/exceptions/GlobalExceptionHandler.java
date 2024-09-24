package com.recipes.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.recipes.dtos.ApiResponceMessage;



@RestControllerAdvice
public class GlobalExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	//handle resourcenotfoundexception
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponceMessage> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
		logger.info("Exception Handler Invoke");
		ApiResponceMessage respoce = ApiResponceMessage.builder().message(ex.getMessage()).status(HttpStatus.NOT_FOUND).success(true).build();
		return new ResponseEntity<>(respoce,HttpStatus.NOT_FOUND);
	}
	
	//methodArgumentnotvalidException
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		
	List<ObjectError> allErrors =ex.getBindingResult().getAllErrors();
	Map<String,Object> response = new HashMap<>();
	allErrors.stream().forEach(objectError -> {
		String message = objectError.getDefaultMessage();
		String field = ((FieldError)objectError).getField();
		response.put(field, message);
	});
	
	return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BadApiRequest.class)
	public ResponseEntity<ApiResponceMessage> handleBadRequest(BadApiRequest ex){
		logger.info("Bad api request");
		ApiResponceMessage respoce = ApiResponceMessage.builder().message(ex.getMessage()).status(HttpStatus.BAD_REQUEST).success(false).build();
		return new ResponseEntity<>(respoce,HttpStatus.BAD_REQUEST);
	}
	
	
}
