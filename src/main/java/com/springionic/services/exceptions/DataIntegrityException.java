package com.springionic.services.exceptions;

public class DataIntegrityException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	//reaproveitando a estrutura do RuntimeException e passando a mensagem
	public DataIntegrityException(String msg) {
		super(msg);
	}
	
	//repassando a mensagem e a causa da exception
	public DataIntegrityException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
