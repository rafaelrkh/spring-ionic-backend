package com.rafael.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Estados implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer cdEstado;
	private String dsEstado;
	
	@JsonIgnore
	@OneToMany(mappedBy="estado")
	private List<Cidades> cidades = new ArrayList<>();
	
	public Estados() {
		
	}

	public Estados(Integer cdEstado, String dsEstado) {
		super();
		this.cdEstado = cdEstado;
		this.dsEstado = dsEstado;
	}

	public Integer getCdEstado() {
		return cdEstado;
	}

	public void setCdEstado(Integer cdEstado) {
		this.cdEstado = cdEstado;
	}

	public String getDsEstado() {
		return dsEstado;
	}

	public void setDsEstado(String dsEstado) {
		this.dsEstado = dsEstado;
	}

	public List<Cidades> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidades> cidades) {
		this.cidades = cidades;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdEstado == null) ? 0 : cdEstado.hashCode());
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
		Estados other = (Estados) obj;
		if (cdEstado == null) {
			if (other.cdEstado != null)
				return false;
		} else if (!cdEstado.equals(other.cdEstado))
			return false;
		return true;
	}
	
	
}
