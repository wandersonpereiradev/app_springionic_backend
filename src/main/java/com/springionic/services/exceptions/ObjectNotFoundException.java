package com.springionic.services.exceptions;

public class ObjectNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	//reaproveitando a estrutura do RuntimeException e passando a mensagem
	public ObjectNotFoundException(String msg) {
		super(msg);
	}
	
	//repassando a mensagem e a causa da exception
	public ObjectNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
