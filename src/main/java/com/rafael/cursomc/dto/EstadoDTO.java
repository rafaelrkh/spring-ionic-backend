package com.rafael.cursomc.dto;

import java.io.Serializable;

import com.rafael.cursomc.domain.Estados;

public class EstadoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
 	private Integer cdEstado;
	private String dsEstado;
	
	public EstadoDTO() {
		
	}
	
	public EstadoDTO(Estados obj) {
		cdEstado = obj.getCdEstado();
		dsEstado = obj.getDsEstado();
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
	
	
}
