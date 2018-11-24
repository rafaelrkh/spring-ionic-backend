package com.rafael.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.rafael.cursomc.services.validation.ClienteInsert;

//Anotação customizada para validações de CPF e CNPJ - TIPO DA PESSOA
@ClienteInsert
public class ClienteNewDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=100, message="O tamanho deve ser entre 5 e 100 caracteres")
	private String dsNome;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Email(message="E-mail inválido!")
	private String dsEmail;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String dsCpfCnpj;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String dsSenha;
	
	private Integer tipo;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String dsTelefone1;
	
	private String dsTelefone2;
	private String dsTelefone3;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String dsLogradouro;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String dsNumero;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String dsBairro;
	
	private String dsComplemento;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String dsCep;	

	private Integer cdCidade;
	
	public String getDsLogradouro() {
		return dsLogradouro;
	}

	public void setDsLogradouro(String dsLogradouro) {
		this.dsLogradouro = dsLogradouro;
	}

	public String getDsNumero() {
		return dsNumero;
	}

	public void setDsNumero(String dsNumero) {
		this.dsNumero = dsNumero;
	}

	public String getDsBairro() {
		return dsBairro;
	}

	public void setDsBairro(String dsBairro) {
		this.dsBairro = dsBairro;
	}

	public String getDsComplemento() {
		return dsComplemento;
	}

	public void setDsComplemento(String dsComplemento) {
		this.dsComplemento = dsComplemento;
	}

	public String getDsCep() {
		return dsCep;
	}

	public void setDsCep(String dsCep) {
		this.dsCep = dsCep;
	}

	
	public ClienteNewDTO() {
		
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

	public String getDsCpfCnpj() {
		return dsCpfCnpj;
	}

	public void setDsCpfCnpj(String dsCpfCnpj) {
		this.dsCpfCnpj = dsCpfCnpj;
	}

	public String getDsSenha() {
		return dsSenha;
	}

	public void setDsSenha(String dsSenha) {
		this.dsSenha = dsSenha;
	}
	

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getDsTelefone1() {
		return dsTelefone1;
	}

	public void setDsTelefone1(String dsTelefone1) {
		this.dsTelefone1 = dsTelefone1;
	}

	public String getDsTelefone2() {
		return dsTelefone2;
	}

	public void setDsTelefone2(String dsTelefone2) {
		this.dsTelefone2 = dsTelefone2;
	}

	public String getDsTelefone3() {
		return dsTelefone3;
	}

	public void setDsTelefone3(String dsTelefone3) {
		this.dsTelefone3 = dsTelefone3;
	}

	public Integer getCdCidade() {
		return cdCidade;
	}

	public void setCdCidade(Integer cdCidade) {
		this.cdCidade = cdCidade;
	}
	
	
}
