package com.example.restfulspringboot.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.restfulspringboot.users.UserNotFoundException;



//@RestController
//@ControllerAdvice
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest req){
		ExceptionResponse res= new ExceptionResponse(new Date(), ex.getMessage(), req.getDescription(false));
		return new ResponseEntity<Object>(res, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleNotFoundException(Exception ex, WebRequest req){
		ExceptionResponse res= new ExceptionResponse(new Date(), ex.getMessage(), req.getDescription(false));
		return new ResponseEntity<Object>(res, HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse res= new ExceptionResponse(new Date(), ex.getBindingResult().toString(), request.getDescription(false));
		return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
	}

}
