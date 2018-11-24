package com.rafael.cursomc.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.rafael.cursomc.domain.enums.EstadoPagamento;

@Entity
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamentos{
	private static final long serialVersionUID = 1L;

	private String dsNumeroCartao;
	private Integer nrParcelas;
	
	public PagamentoComCartao() {
		
	}	

	public PagamentoComCartao(Integer cdPagamento, EstadoPagamento estadoPagamento, Pedidos pedido, String dsNumeroCartao, Integer nrParcelas) {
		super(cdPagamento, estadoPagamento, pedido);
		this.dsNumeroCartao = dsNumeroCartao;
		this.nrParcelas = nrParcelas;
	}



	public String getDsNumeroCartao() {
		return dsNumeroCartao; 
	}

	public void setDsNumeroCartao(String dsNumeroCartao) {
		this.dsNumeroCartao = dsNumeroCartao;
	}

	public Integer getNrParcelas() {
		return nrParcelas;
	}

	public void setNrParcelas(Integer nrParcelas) {
		this.nrParcelas = nrParcelas;
	}
	
	
	
}
