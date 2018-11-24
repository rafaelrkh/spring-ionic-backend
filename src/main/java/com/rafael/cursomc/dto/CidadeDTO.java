package com.rafael.cursomc.dto;

import java.io.Serializable;

import com.rafael.cursomc.domain.Cidades;

public class CidadeDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer cdCidade;
	private String dsCidade;
	
	public CidadeDTO() {
		
	}
	
	public CidadeDTO(Cidades obj) {
		cdCidade = obj.getCdCidade();
		dsCidade = obj.getDsCidade();
	}

	public Integer getCdCidade() {
		return cdCidade;
	}

	public void setCdCidade(Integer cdCidade) {
		this.cdCidade = cdCidade;
	}

	public String getDsCidade() {
		return dsCidade;
	}

	public void setDsCidade(String dsCidade) {
		this.dsCidade = dsCidade;
	}
	
	
	
}
