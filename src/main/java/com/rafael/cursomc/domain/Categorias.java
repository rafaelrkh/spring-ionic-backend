package com.rafael.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class Categorias implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer cdCategoria;
	private String dsCategoria;
	
	
	@ManyToMany(mappedBy="categorias") 
	private List<Produtos> produtos = new ArrayList<>();

	public Categorias() {

	}

	public Categorias(Integer cdCategoria, String cdCategeoria) {
		super();
		this.cdCategoria = cdCategoria;
		this.dsCategoria = cdCategeoria;
	}

	public Integer getCdCategoria() {
		return cdCategoria;
	}

	public void setCdCategoria(Integer cdCategoria) {
		this.cdCategoria = cdCategoria;
	}

	public String getDsCategoria() {
		return dsCategoria;
	}

	public void setDsCategoria(String dsCategoria) {
		this.dsCategoria = dsCategoria;
	}
	
	public List<Produtos> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produtos> produtos) {
		this.produtos = produtos;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdCategoria == null) ? 0 : cdCategoria.hashCode());
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
		Categorias other = (Categorias) obj;
		if (cdCategoria == null) {
			if (other.cdCategoria != null)
				return false;
		} else if (!cdCategoria.equals(other.cdCategoria))
			return false;
		return true;
	}

		
}
