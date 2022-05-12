package com.tweetapp.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tweetapp.model.CustomErrorReponse;


@RestControllerAdvice
public class GlobalExceptionHandler {

	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<CustomErrorReponse> handlesUserNotFoundException(
			UserNotFoundException userNotFoundException) {
		CustomErrorReponse response = new CustomErrorReponse();
		response.setDateTime(LocalDateTime.now());
		response.setMessage(userNotFoundException.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<CustomErrorReponse> handlesUserAlreadyException(UserAlreadyExistsException userAlreadyExistsException){
		CustomErrorReponse response = new CustomErrorReponse();
		response.setDateTime(LocalDateTime.now());
		response.setMessage(userAlreadyExistsException.getMessage());
		return new ResponseEntity<CustomErrorReponse>(response,HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(InvalidPasswordException.class)
	public ResponseEntity<CustomErrorReponse> handlesInvalidPasswordException(InvalidPasswordException invalidPasswordException){
		CustomErrorReponse response = new CustomErrorReponse();
		response.setDateTime(LocalDateTime.now());
		response.setMessage(invalidPasswordException.getMessage());
		return new ResponseEntity<CustomErrorReponse>(response,HttpStatus.UNAUTHORIZED);
	}
}
