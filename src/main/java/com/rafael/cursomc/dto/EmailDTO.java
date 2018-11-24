package com.rafael.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class EmailDTO implements Serializable {
	private static final long serialVersionUID = 1L;	
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Email(message="E-mail inválido!")
	private String dsEmail;
	
	public EmailDTO() {
		
	}

	public String getDsEmail() {
		return dsEmail;
	}

	public void setDsEmail(String dsEmail) {
		this.dsEmail = dsEmail;
	}
	
	
}
