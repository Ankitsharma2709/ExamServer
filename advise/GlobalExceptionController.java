package com.exam.advise;

import javax.naming.CannotProceedException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.exam.customExceptions.BusinessException;
import com.exam.customExceptions.EmptyUserNameException;

import javassist.NotFoundException;

@RestControllerAdvice
public class GlobalExceptionController {
	@ExceptionHandler(EmptyUserNameException.class)
	public ResponseEntity<String> handleEmptyInput(EmptyUserNameException ex){
		return new ResponseEntity<String>("Input feild is empty, Please look into it", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({NotFoundException.class, CannotProceedException.class})
	public ResponseEntity<String> handleCustomException(Exception ex){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id cannot be null");
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid user id provided");
	}
	
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<String>  handleOtherExceptions(Exception ex){
//		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
//	}
//	
	
	

}
