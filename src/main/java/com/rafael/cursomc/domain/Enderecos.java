package com.rafael.cursomc.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Enderecos implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer cdEndereco;
	private String dsLogradouro;
	private String dsNumero;
	private String dsBairro;
	private String dsComplemento;
	private String dsCep;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="cdCliente")
	private Clientes cliente;
	
	@ManyToOne
	@JoinColumn(name="cdCidade")
	private Cidades cidade;
	
	public Enderecos() {
		
	}

	public Enderecos(Integer cdEndereco, String dsLogradouro, String dsNumero, String dsBairro,
			String dsComplemento, String dsCep, Clientes cliente, Cidades cidade) {
		super();
		this.cdEndereco = cdEndereco;
		this.dsLogradouro = dsLogradouro;
		this.dsNumero = dsNumero;
		this.dsBairro = dsBairro;
		this.dsComplemento = dsComplemento;
		this.dsCep = dsCep;
		this.cliente = cliente;
		this.cidade = cidade;
	}

	public Integer getCdEndereco() {
		return cdEndereco;
	}

	public void setCdEndereco(Integer cdEndereco) {
		this.cdEndereco = cdEndereco;
	}

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
	
	

	public Clientes getCliente() {
		return cliente;
	}

	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}
	
	public Cidades getCidade() {
		return cidade;
	}

	public void setCidade(Cidades cidade) {
		this.cidade = cidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdEndereco == null) ? 0 : cdEndereco.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Enderecos other = (Enderecos) obj;
		if (cdEndereco == null) {
			if (other.cdEndereco != null)
				return false;
		} else if (!cdEndereco.equals(other.cdEndereco))
			return false;
		return true;
	}
	
	
}
