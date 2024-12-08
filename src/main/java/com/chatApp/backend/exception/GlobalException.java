package com.chatApp.backend.exception;

import java.time.LocalDateTime;


import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.chatApp.backend.model.User;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler(UserException.class)
	public ResponseEntity<ErrorDetail> UserExceptionHandler(UserException e, WebRequest req) {
		ErrorDetail err = new ErrorDetail(e.getMessage(), req.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<ErrorDetail>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MessageException.class)
	public ResponseEntity<ErrorDetail> MessageExceptionHandler(MessageException ue, WebRequest req) {
		ErrorDetail err = new ErrorDetail(ue.getMessage(), req.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<ErrorDetail>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDetail> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException me, WebRequest req) {
		//String error = me.getBindingResult().getFieldError().getDefaultMessage();
		ErrorDetail err = new ErrorDetail("Validation Error", req.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<ErrorDetail>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorDetail> NoHandlerFoundExceptionHandler(NoHandlerFoundException e, WebRequest req) {
		//String error = e.getBindingResult().getFieldError().getDefaultMessage();
		ErrorDetail err = new ErrorDetail("Endpoint Not Found", req.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<ErrorDetail>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetail> otherExceptionHandler(Exception e, WebRequest req) {
		ErrorDetail err = new ErrorDetail(e.getMessage(), req.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<ErrorDetail>(err, HttpStatus.BAD_REQUEST);
	}
}
