package com.rafael.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.rafael.cursomc.domain.Clientes;
import com.rafael.cursomc.services.validation.ClienteUpdate;

@ClienteUpdate
public class ClienteDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer cdCliente;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=100, message="O tamanho deve ser entre 5 e 100 caracteres")
	private String dsNome;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Email(message="E-mail inválido!")
	private String dsEmail;
	
	public ClienteDTO() {
		
	}
	
	public ClienteDTO(Clientes obj) {
		cdCliente = obj.getCdCliente();
		dsNome = obj.getDsNome();
		dsEmail = obj.getDsEmail();
	}

	public Integer getCdCliente() {
		return cdCliente;
	}

	public void setCdCliente(Integer cdCliente) {
		this.cdCliente = cdCliente;
	}

	public String getDsNome() {
		return dsNome;
	}

	public void setDsNome(String dsNome) {
		this.dsNome = dsNome;
	}

	public String getDsEmail() {
		return dsEmail;
	}

	public void setDsEmail(String dsEmail) {
		this.dsEmail = dsEmail;
	}
	
	 

}
