package com.ceiba.parkingceiba.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ceiba.parkingceiba.exception.tipos.ParqueaderoErrorBuilderException;

public class ParqueaderoHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ParqueaderoErrorBuilderException.class)
	protected ResponseEntity<Object> handleOperacionInvalida(ParqueaderoErrorBuilderException exception){
	return new ResponseEntity<> (exception.getMensaje(), exception.getHttpStatus());
	}
}