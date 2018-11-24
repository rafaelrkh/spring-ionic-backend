package com.rafael.cursomc.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotEmpty;

import com.rafael.cursomc.domain.Categorias;

public class CategoriaDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	//Define os dados que serão trafegados quando for fazer operação
	
	private Integer cdCategoria;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5,max=80,message="O tamanho deve ser maior do que 5 e menor do que 80 caracteres")
	private String dsCategoria;
	
	public CategoriaDTO() {
		
	}
	
	public CategoriaDTO(Categorias obj) {
		cdCategoria = obj.getCdCategoria();
		dsCategoria = obj.getDsCategoria();
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
	
	 
}
