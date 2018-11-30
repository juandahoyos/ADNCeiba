package com.ceiba.parkingceiba.exception.tipos;

import org.springframework.http.HttpStatus;

public class ParqueaderoErrorBuilderException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final String mensaje;
	private final HttpStatus httpStatus;
	
	
	public ParqueaderoErrorBuilderException(String mensaje, HttpStatus httpStatus) {
		super();
		this.mensaje = mensaje;
		this.httpStatus = httpStatus;
	}

	public String getMensaje() {
		return mensaje;
	}


	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	
}
