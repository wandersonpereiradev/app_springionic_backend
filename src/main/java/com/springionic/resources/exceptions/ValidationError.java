package com.springionic.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

//classe que herda da classe StandardError e também utiliza os campos da classe FieldMessage

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> errors = new ArrayList<>();
	
	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
		
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	// o método get foi substituído pois o interesse é capturar um erro de cada vez
	public void addError(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName, message));
	}

	
	
	

}
