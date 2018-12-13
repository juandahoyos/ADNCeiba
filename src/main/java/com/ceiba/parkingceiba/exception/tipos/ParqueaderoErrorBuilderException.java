package com.ceiba.parkingceiba.exception.tipos;

import org.springframework.http.HttpStatus;

public class ParqueaderoErrorBuilderException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final HttpStatus httpStatus;
	
	
	public ParqueaderoErrorBuilderException(String mensaje, HttpStatus httpStatus) {
		super(mensaje);
		this.httpStatus = httpStatus;
	}

	public String getMensaje() {
		return this.getMessage();
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
