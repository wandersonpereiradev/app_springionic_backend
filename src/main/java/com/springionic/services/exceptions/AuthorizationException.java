package com.springionic.services.exceptions;

public class AuthorizationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	//reaproveitando a estrutura do RuntimeException e passando a mensagem
	public AuthorizationException(String msg) {
		super(msg);
	}
	
	//repassando a mensagem e a causa da exception
	public AuthorizationException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
