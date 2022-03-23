package com.prova.provabackend.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.prova.provabackend.exception.ExceptionResponse;

@ControllerAdvice
@RestController
public class Handler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(ExceptionResponse.class)
	public final ResponseEntity<ExceptionResponse> handlerBadRequestException(Exception e, WebRequest request){
		ExceptionResponse exceptionresponse = new ExceptionResponse(new Date(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity<ExceptionResponse>(exceptionresponse, HttpStatus.BAD_REQUEST);
	}

}
