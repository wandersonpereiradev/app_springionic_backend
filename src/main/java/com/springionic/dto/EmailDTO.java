package com.springionic.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class EmailDTO implements Serializable {	//só para receber o email do "forgot" no AuthResource
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Preenchimento obrigatório.")
	@Email(message = "Email inválido!")
	private String email;
	
	public EmailDTO() {}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

}
