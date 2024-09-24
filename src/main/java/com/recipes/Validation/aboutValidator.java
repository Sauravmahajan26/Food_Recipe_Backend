package com.recipes.Validation;

import java.lang.annotation.Annotation;


import org.slf4j.Logger;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class aboutValidator implements ConstraintValidator<aboutValidate,String>{

	private Logger logger = org.slf4j.LoggerFactory.getLogger(aboutValidator.class); 
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		logger.info("message from isValid : {}",value);
		if(value.isBlank()) {
			return false;
		}else {
			return true;
		}
		
	}


}
